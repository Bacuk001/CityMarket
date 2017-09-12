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
import by.intexsoft.configuration.service.MarketService;
import by.intexsoft.configuration.service.ProductService;
import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Product;

@RestController
public class ProductMarketContoller {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	private static final String PRODUCTS_NOT_FOUND = "Products not found.";
	private static final String MESSAGE = "Message";
	MarketService marketService;
	CategoryService categoryService;
	ProductService productService;

	@Autowired
	public ProductMarketContoller(MarketService marketService, CategoryService categoryService,
			ProductService productService) {
		this.marketService = marketService;
		this.categoryService = categoryService;
		this.productService = productService;
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

}
