package by.intexsoft.configuration.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.configuration.service.MarketService;
import by.intexsoft.configuration.service.StockService;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Stock;

@RestController
public class StockRestContrioller {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockRestContrioller.class);
	private static final String MESSAGE = "Message";
	private StockService stockService;
	private MarketService marketService;

	public StockRestContrioller(MarketService marketService, StockService stockService) {
		this.stockService = stockService;
		this.marketService = marketService;
	}

	@RequestMapping(value = "/stock/save", method = RequestMethod.POST)
	public ResponseEntity<Stock> saveStock(@RequestBody Stock stock) {
		LOGGER.info("Save ctock to database.");
		stock = stockService.save(stock);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (stock == null) {
			headers.add(MESSAGE, "Do not save to database.");
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Stock>(stock, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/stocks", method = RequestMethod.GET)
	public ResponseEntity<List<Stock>> getAllStocks() {
		LOGGER.info("Find all stock.");
		List<Stock> stocks = stockService.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (stocks == null) {
			headers.add(MESSAGE, "Not Found");
			return new ResponseEntity<List<Stock>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Stock>>(stocks, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/stocks/market/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Stock>> getStockByMarket(@PathVariable("id") int idMarket) {
		LOGGER.info("Find all stock by market.");
		Market market = marketService.findOne(idMarket);
		List<Stock> stocks = stockService.finfByMasrket(market);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (stocks == null) {
			headers.add(MESSAGE, "Not Found");
			return new ResponseEntity<List<Stock>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Stock>>(stocks, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/stock/sign/market/{idMarket}", method = RequestMethod.POST)
	public ResponseEntity<List<Stock>> signStockForMerket(@RequestBody List<Stock> stocks,
			@PathVariable("idMarket") int idMarket) {
		LOGGER.info("Sign stock for market.");
		stocks = stockService.signStockforMarket(stocks, idMarket);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (stocks == null) {
			headers.add(MESSAGE, "Do not save.");
			return new ResponseEntity<List<Stock>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Stock>>(stocks, headers, HttpStatus.OK);
	}
}
