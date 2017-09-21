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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import by.intexsoft.configuration.service.MarketService;
import by.intexsoft.configuration.service.PriceServicse;
import by.intexsoft.configuration.service.ProductService;
import by.intexsoft.configuration.service.StockService;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Price;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.PriceRepository;

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
	private static final Logger LOGGER = LoggerFactory.getLogger(PriceRestController.class);
	private static final String MESSAGE = "Message";
	private PriceServicse priceServicse;
	private ProductService productService;
	private StockService stockService;
	private MarketService marketService;

	@Autowired
	PriceRestController(MarketService marketService, StockService stockService, ProductService productService,
			PriceServicse priceServicse) {
		this.priceServicse = priceServicse;
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
		LOGGER.info("Find prices by product and market.");
		Product product = productService.findOne(idProduct);
		Market market = marketService.findOne(idmarket);
		List<Stock> stocks = stockService.finfByMasrket(market);
		List<Price> prices = priceServicse.findByProductAndStocks(product, stocks);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (prices == null) {
			headers.add(MESSAGE, "Prices not fond.");
			return new ResponseEntity<List<Price>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Price>>(prices, headers, HttpStatus.OK);
	}

	/**
	 * A controller method that processes requests to save prices.
	 */
	@RequestMapping(value = "/price/save", method = RequestMethod.POST)
	public ResponseEntity<Price> save(@RequestBody Price price) {
		LOGGER.info("Save price product for stock.");
		price = priceServicse.save(price);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (price == null) {
			headers.add(MESSAGE, "Price do not save.");
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Price>(price, headers, HttpStatus.OK);
	}
    /**
    */
    @RequestMapping(value = "/prices/product/{idProduct}/stock/{idStock}", method = RequestMethod.GET)
	public ResponseEntity<Price> getPriseByProductAndStock(@PathVariable("idProduct") int idProduct,
			@PathVariable("idStock") int idStock) {
		LOGGER.info("Find prices by product and market.");
		Product product = productService.findOne(idProduct);
		Stock stock= stockService.findOne(idStock);
		Price prices = priceServicse.findByProductAndStock(product, stock);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (prices == null) {
			headers.add(MESSAGE, "Prices not fond.");
			return new ResponseEntity<Price>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Price>(prices, headers, HttpStatus.OK);
	}

}
