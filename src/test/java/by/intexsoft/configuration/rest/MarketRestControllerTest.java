package by.intexsoft.configuration.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import by.intexsoft.entity.Market;
import by.intexsoft.rest.MarketRestController;
import by.intexsoft.service.impl.MarketService;

@RunWith(MockitoJUnitRunner.class)
public class MarketRestControllerTest {
	private static final String MESSAGE = "Message";
	private static final String MARKET_NOT_FOUND = "Market not found.";
	@Mock
	private MarketService marketService;
	@InjectMocks
	private MarketRestController marketRestController = new MarketRestController(marketService);

	@Test
	public void saveTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		Market market = new Market();
		when(marketService.save(market)).thenReturn(market);
		ResponseEntity<Market> response = new ResponseEntity<Market>(market, headers, HttpStatus.OK);
		assertEquals(marketRestController.save(market), response);
		when(marketService.save(market)).thenReturn(null);
		headers.add(MESSAGE, "Market do not save.");
		response = new ResponseEntity<Market>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(marketRestController.save(market), response);
	}

	@Test
	public void getAllMarketsTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		List<Market> markets = new ArrayList<>();
		when(marketService.findAllMarket()).thenReturn(markets);
		ResponseEntity<List<Market>> response = new ResponseEntity<List<Market>>(markets, headers, HttpStatus.OK);
		assertEquals(marketRestController.getAllMarket(), response);
	}

	@Test
	public void getByIdTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		Market market = new Market();
		when(marketService.findOne(5)).thenReturn(market);
		when(marketService.findOne(0)).thenReturn(null);
		ResponseEntity<Market> response = new ResponseEntity<Market>(market, headers, HttpStatus.OK);
		assertEquals(marketRestController.getById(5), response);
		headers.add(MESSAGE, MARKET_NOT_FOUND);
		response = new ResponseEntity<Market>(headers, HttpStatus.NOT_FOUND);
		assertEquals(marketRestController.getById(0), response);
	}
}
