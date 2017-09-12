package by.intexsoft.configuration.rest;

import java.util.List;
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
import by.intexsoft.configuration.service.MarketService;
import by.intexsoft.configuration.service.ProductService;
import by.intexsoft.configuration.service.StockService;
import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;

@RestController
public class ProductRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	private static final String PRODUCTS_NOT_FOUND = "Products not found.";
	private static final String MESSAGE = "Message";
	private ProductService productService;
	private MarketService marketService;
	private StockService stockService;
	private CategoryService categoryService;

	@Autowired
	ProductRestController(CategoryService categoryService, ProductService productService, MarketService marketService,
			StockService stockService) {
		this.productService = productService;
		this.marketService = marketService;
		this.stockService = stockService;
		this.categoryService = categoryService;
	}

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

	@RequestMapping(value = "/products/market/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductsBymarket(@PathVariable("id") int id) {
		LOGGER.info("Find prodycts by market.");
		Market market = marketService.findOne(id);
		List<Product> products = productService.findByMarket(market);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			new ResponseEntity<List<Product>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

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

	@RequestMapping(value = "/products/market/{idMarket}/category/{idCategory}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getByMarketAndCategiry(@PathVariable("idMarket") int idMarket,
			@PathVariable("idCategory") int idCategory) {
		LOGGER.info("Find product by market and category.");
		Market market = marketService.findOne(idMarket);
		Category category = categoryService.findOne(idCategory);
		List<Product> products = productService.findByMarketAndCategory(market, category);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<List<Product>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/stock/{idStock}/category/{idCategory}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getByStockAndCategory(@PathVariable("idStock") int idStock,
			@PathVariable("idCategory") int idCategory) {
		LOGGER.info("Find product by stock and category.");
		Stock stock = stockService.findOne(idStock);
		Category category = categoryService.findOne(idStock);
		List<Product> products = productService.findByStockAndCategory(stock, category);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<List<Product>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

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

	@RequestMapping(value = "/products/market/{idMarket}/category/{idCategory}/count", method = RequestMethod.GET)
	public ResponseEntity<Integer> conuByMarketAndCategory(@PathVariable("idMarket") int idMarket,
			@PathVariable("idCategory") int idCategory) {
		LOGGER.info("Cont product by stock and category.");
		Market market = marketService.findOne(idMarket);
		Category category = categoryService.findOne(idMarket);
		Integer countProducts = productService.countByMarketsAndCategory(market, category);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (countProducts == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<Integer>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Integer>(countProducts, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/product/save", method = RequestMethod.POST)
	public ResponseEntity<Product> save(@RequestBody Product product) {
		product = productService.save(product);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (product == null) {
			headers.add(MESSAGE, "Product don't save");
			return new ResponseEntity<Product>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Product>(product, headers, HttpStatus.OK);
	}

}
