package by.intexsoft.rest;

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

import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Price;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.PriceRepository;
import by.intexsoft.service.IMarketService;
import by.intexsoft.service.IPriceService;
import by.intexsoft.service.IProductService;
import by.intexsoft.service.IStockService;
import by.intexsoft.service.impl.PriceServicse;

/**
 * A controller that processes requests for prices information The controller
 * receives requests, processes the information, and returns the responses to
 * the user. The controller can receive the object and send it to the
 * {@link PriceServicse} repository, it can handle the transfer control of the
 * {@link PriceServicse} to delete the price.
 * 
 * @see {@link PriceServicse},{@link RestController}, {@link Price},
 *      {@link PriceRepository}
 */
@RestController
public class PriceRestController {
	private static final String GET_COUNT_PRICE_OF_PRODUCT_IN_MARKET_AND_CATEGORY = "Get count price of product in market and category.";
	private static final String FIND_PRICE_BY_MARKET_AND_CATEGORY = "Find price by market and category.";
	private static final String PRICE_NOT_SAVE = "Price do not save.";
	private static final String SAVE_PRICE = "Save price product for stock.";
	private static final String APPLICATION_JSON = "application/json; charset=UTF-8";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String FIND_PRICES_BY_PRODUCT_AND_MARKET = "Find prices by product and market.";
	private static final Logger LOGGER = LoggerFactory.getLogger(PriceRestController.class);
	private static final String MESSAGE = "Message";
	private IPriceService priceServise;
	private IProductService productService;
	private IStockService stockService;
	private IMarketService marketService;

	@Autowired
	public PriceRestController(IMarketService marketService, IStockService stockService, IProductService productService,
			IPriceService priceServicse) {
		this.priceServise = priceServicse;
		this.productService = productService;
		this.stockService = stockService;
		this.marketService = marketService;
	}

	/**
	 * The controller method that processes requests for product prices in the
	 * store.
	 * 
	 * @see {@link Product}, {@link Market}
	 */
	@RequestMapping(value = "/prices/product/{idProduct}/market/{idMarket}", method = RequestMethod.GET)
	public ResponseEntity<List<Price>> getPriseByProductAndMarket(@PathVariable("idProduct") int idProduct,
			@PathVariable("idMarket") int idmarket) {
		LOGGER.info(FIND_PRICES_BY_PRODUCT_AND_MARKET);
		Product product = productService.findOne(idProduct);
		Market market = marketService.findOne(idmarket);
		List<Stock> stocks = stockService.finfByMasrket(market);
		List<Price> prices = priceServise.findByProductAndStocks(product, stocks);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		return new ResponseEntity<List<Price>>(prices, headers, HttpStatus.OK);
	}

	/**
	 * A controller method that processes requests to save prices.
	 */
	@RequestMapping(value = "/price/save", method = RequestMethod.POST)
	public ResponseEntity<Price> save(@RequestBody Price price) {
		LOGGER.info(SAVE_PRICE);
		price = priceServise.save(price);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		if (price == null) {
			headers.add(MESSAGE, PRICE_NOT_SAVE);
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Price>(price, headers, HttpStatus.OK);
	}

	/**
	 * The controller's method accepts a request for receipt of prices for the
	 * product by the warehouse. Contact the service to get the price. Gets the
	 * data, generates and sends a response.
	 * 
	 * @see {@link PriceServicse}, {@link Product}, {@link Stock},
	 *      {@link PriceRepository}
	 */
	@RequestMapping(value = "/prices/product/{idProduct}/stock/{idStock}", method = RequestMethod.GET)
	public ResponseEntity<List<Price>> getPriseByProductAndStock(@PathVariable("idProduct") int idProduct,
			@PathVariable("idStock") int idStock) {
		LOGGER.info(FIND_PRICES_BY_PRODUCT_AND_MARKET);
		Product product = productService.findOne(idProduct);
		Stock stock = stockService.findOne(idStock);
		List<Price> prices = priceServise.findByProductAndStock(product, stock);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		return new ResponseEntity<List<Price>>(prices, headers, HttpStatus.OK);
	}

	/**
	 * The controller's method accepts requests for prices for products that are
	 * available in the store in a certain category, prices are sorted by age and
	 * returned in a page.
	 * 
	 * @see {@link PriceServicse}
	 */
	@RequestMapping(value = "/prices/market/{idMarket}/category/{idCategory}/direction/{direction}/pageSize/{sizePage}/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<List<Price>> getPricesByMarketAndCtegorySortAndPage(
			@PathVariable("idCategory") int idCategory, @PathVariable("idMarket") int idMarket,
			@PathVariable("direction") String direction, @PathVariable("sizePage") int sizePage,
			@PathVariable("page") int page) {
		LOGGER.info(FIND_PRICE_BY_MARKET_AND_CATEGORY);
		List<Price> prices = priceServise.findPriceByMarketAndCategory(idCategory, idMarket, direction, sizePage, page);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		return new ResponseEntity<List<Price>>(prices, headers, HttpStatus.OK);
	}

	/**
	 * The repository method accesses the service to obtain the number of price
	 * records for the list of products in the warehouses that are transferred in
	 * the parameters. Products that are not in stock are excluded.
	 * 
	 * @see {@link PriceServicse}, {@link Market}, {@link Category}
	 */
	@RequestMapping(value = "/prices/market/{idMarket}/category/{idCategory}/count", method = RequestMethod.GET)
	public ResponseEntity<Integer> countPriceOfProductByMarketAndCategory(@PathVariable("idCategory") int idCategory,
			@PathVariable("idMarket") int idMarket) {
		LOGGER.info(GET_COUNT_PRICE_OF_PRODUCT_IN_MARKET_AND_CATEGORY);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		Integer countPrice = priceServise.countPricesOfProductsMyMarketAndCategory(idCategory, idMarket);
		return new ResponseEntity<Integer>(countPrice, headers, HttpStatus.OK);
	}
}
