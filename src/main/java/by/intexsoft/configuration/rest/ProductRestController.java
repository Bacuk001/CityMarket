package by.intexsoft.configuration.rest;

import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import by.intexsoft.configuration.service.CategoryService;
import by.intexsoft.configuration.service.ProductService;
import by.intexsoft.configuration.service.StockService;
import by.intexsoft.entity.Category;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.PriceRepository;

/**
 * A controller that processes requests for information about products. The
 * controller receives requests, processes the information, and returns the
 * responses to the user. The controller can receive the object and send it to
 * the {@link ProductService} repository, it can handle the transfer control of
 * the {@link ProductService} to delete and add the product.
 * 
 * @see {@link ProductService}, {@link RestController}, {@link Product},
 *      {@link PriceRepository}
 */
@RestController
public class ProductRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	private static final String PRODUCTS_NOT_FOUND = "Products not found.";
	private static final String MESSAGE = "Message";
	private ProductService productService;
	private CategoryService categoryService;
	private StockService stockService;

	@Autowired
	ProductRestController(StockService stockService, CategoryService categoryService, ProductService productService) {
		this.stockService = stockService;
		this.productService = productService;
		this.categoryService = categoryService;
	}

	/**
	 * The controller processes requests for the product by its id.
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getById(@PathVariable("id") int id) {
		LOGGER.info("Find product by id.");
		Product product = productService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (product == null) {
			LOGGER.error("Error find product by id.");
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<Product>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Product>(product, headers, HttpStatus.OK);
	}

	/**
	 * The controller processes requests for products in the category.
	 * 
	 * @see {@link Category}
	 */
	@RequestMapping(value = "/products/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getByCategory(@PathVariable("id") int id) {
		LOGGER.info("Find products by category.");
		Category category = categoryService.findOne(id);
		List<Product> products = productService.findByCategory(category);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<List<Product>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

	/**
	 * Preservation of the product.
	 */
	@Transactional
	@RequestMapping(value = "/product/save/category/{idCategory}/stock/{idStock}", method = RequestMethod.POST)
	public ResponseEntity<Product> save(@RequestBody Product product, @PathVariable("idCategory") int idCategory,
			@PathVariable("idStock") int idStock) {
		LOGGER.info("Save product.");
		Stock stock = stockService.findOne(idStock);
		Category category = categoryService.findOne(idCategory);
		product.category = category;
		product = productService.save(product);
		stock.product.add(product);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (product == null) {
			headers.add(MESSAGE, "Product don't save");
			return new ResponseEntity<Product>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Product>(product, headers, HttpStatus.OK);
	}
}
