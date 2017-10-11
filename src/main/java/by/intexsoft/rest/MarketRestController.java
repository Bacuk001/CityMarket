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
import by.intexsoft.entity.Market;
import by.intexsoft.service.IMarketService;
import by.intexsoft.service.impl.MarketService;

/**
 * A controller that processes requests for information about the market. The
 * controller receives requests, processes the information, and returns the
 * responses to the user. The controller can receive the object and send it to
 * the {@link MarketService} repository, it can process the transfer control of
 * the {@link MarketService} to remove the market.
 *
 * @see {@link RestController}, {@link Market}, {@link MarketService}
 */
@RestController
public class MarketRestController {
	private static final String GET_ALL_MARKETS = "Get all markets.";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String MARKET_DO_NOT_SAVE = "Market do not save.";
	private static final String SAVE_MARKET_TO_DATABASE = "Save market to database.";
	private static final String APPLICATION_JSON = "application/json; charset=UTF-8";
	private static final String MARKET_NOT_FOUND = "Market not found.";
	private static final String DELETE_MARKET = "Delete market.";
	private static final Logger LOGGER = LoggerFactory.getLogger(MarketRestController.class);
	private static final String MESSAGE = "Message";
	private IMarketService marketService;

	@Autowired
	public MarketRestController(IMarketService marketService) {
		this.marketService = marketService;
	}

	/**
	 * The method of the controller accepting requests for the deletion of the
	 * store.
	 */
	@RequestMapping(value = "/market/save", method = RequestMethod.POST)
	public ResponseEntity<Market> save(@RequestBody Market market) {
		LOGGER.info(SAVE_MARKET_TO_DATABASE);
		market = marketService.save(market);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		if (market == null) {
			headers.add(MESSAGE, MARKET_DO_NOT_SAVE);
			return new ResponseEntity<Market>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Market>(market, headers, HttpStatus.OK);
	}

	/**
	 * A controller method that receives requests for a list of all stores.
	 */
	@RequestMapping(value = "/markets", method = RequestMethod.GET)
	public ResponseEntity<List<Market>> getAllMarket() {
		LOGGER.info(GET_ALL_MARKETS);
		List<Market> markets = marketService.findAllMarket();
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		return new ResponseEntity<List<Market>>(markets, headers, HttpStatus.OK);
	}

	/**
	 * A controller method that accepts store deletion requests.
	 */
	@RequestMapping(value = "/market/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Market> delete(@PathVariable("id") int id) {
		LOGGER.info(DELETE_MARKET);
		marketService.delete(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		return new ResponseEntity<Market>(headers, HttpStatus.OK);
	}

	/**
	 * The method of the controller accepting requests for receipt of the store on
	 * his id.
	 */
	@RequestMapping(value = "/market/{id}", method = RequestMethod.GET)
	public ResponseEntity<Market> getById(@PathVariable("id") int id) {
		Market market = marketService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		if (market == null) {
			headers.add(MESSAGE, MARKET_NOT_FOUND);
			return new ResponseEntity<Market>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Market>(market, headers, HttpStatus.OK);
	}
}
