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

import by.intexsoft.configuration.service.MarketService;
import by.intexsoft.entity.Market;

@RestController
public class MarketRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MarketRestController.class);
	private static final String MESSAGE = "Message";
	private MarketService marketService;

	@Autowired
	public MarketRestController(MarketService marketService) {
		this.marketService = marketService;
	}

	@RequestMapping(value = "/market/save", method = RequestMethod.POST)
	public ResponseEntity<Market> save(@RequestBody Market market) {
		LOGGER.info("Save market to database");
		market = marketService.save(market);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (market == null) {
			headers.add(MESSAGE, "Market do not save.");
			return new ResponseEntity<Market>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Market>(market, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/markets", method = RequestMethod.GET)
	public ResponseEntity<List<Market>> getAllMarket() {
		LOGGER.info("Get all markets.");
		List<Market> markets = marketService.findAllMarket();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (markets == null) {
			headers.add(MESSAGE, "Market do not save.");
			return new ResponseEntity<List<Market>>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Market>>(markets, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/market/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Market> delete(@PathVariable("id") int id) {
		LOGGER.info("Delete market.");
		marketService.delete(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		return new ResponseEntity<Market>(headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/market/{id}", method = RequestMethod.GET)
	public ResponseEntity<Market> getById(@PathVariable("id") int id) {
		Market market = marketService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (market == null) {
			headers.add(MESSAGE, "Market not found.");
			return new ResponseEntity<Market>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Market>(market, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/market/sign/{idMarket}/{idStock}")
	public ResponseEntity<Market> signStock(@PathVariable("idMarket") int inMarket,
			@PathVariable("idStock") int idStock) {
		marketService.signMarketInStock(inMarket, idStock);
		return new ResponseEntity<Market>(HttpStatus.OK);
	}

}
