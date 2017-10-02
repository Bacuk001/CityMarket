package by.intexsoft.configuration.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
 * A controller that processes requests for information about products with
 * which the stock is running. The controller receives requests, processes the
 * information, and returns the responses to the user. The controller can
 * receive the object and send it to the {@link ProductService} repository, it
 * can handle get content of the {@link ProductService}.
 * 
 * @see {@link ProductService}, {@link RestController}, {@link Product},
 *      {@link PriceRepository}, {@link Stock}, {@link StockService}
 */
@RestController
public class ProductStockRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	private static final String PRODUCTS_NOT_FOUND = "Products not found.";
	private static final String MESSAGE = "Message";
	private StockService stockService;
	private ProductService productService;
	private CategoryService categoryService;

	@Autowired
	public ProductStockRestController(StockService stockService, ProductService productService,
			CategoryService categoryService) {
		this.productService = productService;
		this.stockService = stockService;
		this.categoryService = categoryService;
	}

	/**
	 * The controller method that processes requests for products in the stock.
	 */
	@RequestMapping(value = "/products/stock/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductsByStock(@PathVariable("id") int id) {
		LOGGER.info("Find product by stock id.");
		Stock stock = stockService.findOne(id);
		List<Product> products = productService.finByStock(stock);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<List<Product>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

	/**
	 * The controller method that processes requests for products in the warehouse
	 * of the corresponding category.
	 * 
	 * @see {@link Category}
	 */
	@RequestMapping(value = "/products/stock/{idStock}/category/{idCategory}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getByStockAndCategory(@PathVariable("idStock") int idStock,
			@PathVariable("idCategory") int idCategory) {
		LOGGER.info("Find product by stock and category.");
		Stock stock = stockService.findOne(idStock);
		Category category = categoryService.findOne(idCategory);
		List<Product> products = productService.findByStockAndCategory(stock, category);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<List<Product>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

	/**
	 * The controller method that processes requests for the number of products in a
	 * warehouse of the corresponding category.
	 */
	@RequestMapping(value = "/products/stock/{idStock}/category/{idCategory}/count", method = RequestMethod.GET)
	public ResponseEntity<Integer> conuByStockAndCategory(@PathVariable("idStock") int idStock,
			@PathVariable("idCategory") int idCategory) {
		LOGGER.info("Cont product by stock and category.");
		Stock stock = stockService.findOne(idStock);
		Category category = categoryService.findOne(idStock);
		Integer countProducts = productService.countByStockAndCategory(stock, category);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (countProducts == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<Integer>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Integer>(countProducts, headers, HttpStatus.OK);
	}
}
