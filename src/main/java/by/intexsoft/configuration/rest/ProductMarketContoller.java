package by.intexsoft.configuration.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import by.intexsoft.configuration.service.CategoryService;
import by.intexsoft.configuration.service.MarketService;
import by.intexsoft.configuration.service.PriceServicse;
import by.intexsoft.configuration.service.ProductService;
import by.intexsoft.configuration.service.StockService;
import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Price;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.PriceRepository;
import by.intexsoft.repository.ProductRepository;

/**
 * A controller that processes requests for information about products with
 * which the market is running. The controller receives requests, processes the
 * information, and returns the responses to the user. The controller can
 * receive the object and send it to the {@link ProductService} repository, it
 * can handle the transfer control of the {@link ProductService}.
 * 
 * @see {@link ProductService}, {@link RestController}, {@link Product},
 *      {@link PriceRepository}
 */
@RestController
public class ProductMarketContoller {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	private static final String PRODUCTS_NOT_FOUND = "Products not found.";
	private static final String MESSAGE = "Message";
	private MarketService marketService;
	private CategoryService categoryService;
	private ProductService productService;
	private StockService stockService;
	private PriceServicse priceServicse;

	@Autowired
	public ProductMarketContoller(MarketService marketService, CategoryService categoryService,
			ProductService productService, StockService stockService, PriceServicse priceServicse) {
		this.marketService = marketService;
		this.priceServicse = priceServicse;
		this.categoryService = categoryService;
		this.productService = productService;
		this.stockService = stockService;
	}

	/**
	 * The controller method that processes requests for the count of products on
	 * the market in the corresponding category.
	 * 
	 * @see {@link Category}
	 */
	@RequestMapping(value = "/products/count/market/{idMarket}/category/{idCategory}", method = RequestMethod.GET)
	public ResponseEntity<Integer> conuntByMarketAndCategory(@PathVariable("idMarket") int idMarket,
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

	/**
	 * The controller method that processes requests for products on the market.
	 */
	@RequestMapping(value = "/products/market/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductsByMarket(@PathVariable("id") int id) {
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

	/**
	 * The controller method that processes requests for products on the market in
	 * the corresponding category.
	 * 
	 * @see {@link Category}, {@link Market},
	 */
	@RequestMapping(value = "/products/market/{idMarket}/category/{idCategory}/all", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getByMarketAndCategiry(@PathVariable("idMarket") int idMarket,
			@PathVariable("idCategory") int idCategory) {
		LOGGER.info("Find product by market and category.");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		Market market = marketService.findOne(idMarket);
		List<Stock> stocks = stockService.finfByMasrket(market);
		Category category = categoryService.findOne(idCategory);
		List<Product> products = productService.findByMarketAndCategory(market, category);
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<List<Product>>(headers, HttpStatus.NO_CONTENT);
		}
		for (int index = 0; index < products.size(); index++) {
			List<Price> prices = priceServicse.findByProductAndStocks(products.get(index), stocks);
			products.get(index).prices = prices;
		}
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

	/**
	 * Controller processing requests for products with which the market operates,
	 * in a certain category. the controller takes the category id in which the
	 * product and the market id that sells this product are located.
	 * 
	 * @see {@link MarketService}, {@link Market} , {@link Stock},
	 *      {@link StockService}, {@link CategoryService}, {@link Category}
	 */
	@RequestMapping(value = "/products/market/{idMarket}/category/{idCategory}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductByCategoryAndMarket(@PathVariable("idCategory") int idCategory,
			@PathVariable("idMarket") int idMarket) {
		Category category = categoryService.findOne(idCategory);
		Market market = marketService.findOne(idMarket);
		List<Stock> stocks = stockService.finfByMasrket(market);
		List<Product> products = productService.findByCategoryAndStocks(category, stocks);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<List<Product>>(headers, HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

	/**
	 * The controller processes requests for products from the category of goods on
	 * the rank in which to contain. The controller returns the quantity of goods
	 * corresponding to the quantity indicated in the page size and the goods that
	 * are contained in the page.
	 * 
	 * @see {@link Product}, {@link Pageable}, {@link ProductService},
	 *      {@link ProductRepository}
	 */
	@RequestMapping(value = "/products/market/{idMarket}/category/{idCategory}/sizePage/{sizePage}/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductByMarketPagable(@PathVariable("idMarket") int idMarket,
			@PathVariable("idCategory") int idCategory, @PathVariable("sizePage") int sizePage,
			@PathVariable("page") int page) {
		LOGGER.info("Find product by stock and category on page.");
		Category category = categoryService.findOne(idCategory);
		Market market = marketService.findOne(idMarket);
		List<Stock> stocks = stockService.finfByMasrket(market);
		List<Product> products = productService.findByCategoryAndStocksPage(category, stocks,
				new PageRequest(page, sizePage));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<List<Product>>(headers, HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
	}

	/**
	 * The controller will receive a request for information on the number of
	 * products in the category in the store. Forms objects and requests information
	 * from the service. if the answer is positive, the answer is that the data is
	 * received. if data is not received, sends a server error.
	 * 
	 * @see {@link Product}, {@link ProductService}, {@link ProductRepository}
	 */
	@RequestMapping(value = "/products/market/{idMarket}/category/{idCategory}/count", method = RequestMethod.GET)
	public ResponseEntity<Integer> countProductByMarket(@PathVariable("idMarket") int idMarket,
			@PathVariable("idCategory") int idCategory) {
		LOGGER.info("Find product by stock and category on page.");
		Category category = categoryService.findOne(idCategory);
		Market market = marketService.findOne(idMarket);
		List<Stock> stocks = stockService.finfByMasrket(market);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		Integer products = productService.countProductByCategoryAndStocks(category, stocks);
		if (products == null) {
			headers.add(MESSAGE, PRODUCTS_NOT_FOUND);
			return new ResponseEntity<Integer>(headers, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(products, headers, HttpStatus.OK);
	}
}
