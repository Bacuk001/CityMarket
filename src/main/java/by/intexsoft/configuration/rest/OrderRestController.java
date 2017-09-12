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

import by.intexsoft.configuration.service.MarketService;
import by.intexsoft.configuration.service.OrderService;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Order;

@RestController
public class OrderRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);
	private static final String ORDERS_NOT_FOUND = "Orders not found.";
	private static final String MESSAGE = "Message";
	private OrderService orderService;
	private MarketService marketService;

	@Autowired
	public OrderRestController(OrderService orderService, MarketService marketService) {
		this.orderService = orderService;
		this.marketService = marketService;
	}

	@RequestMapping(name = "/order/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> getById(@PathVariable("id") int id) {
		Order order = orderService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (order == null) {
			headers.add(MESSAGE, "Order not found.");
			return new ResponseEntity<Order>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(order, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getAll() {
		List<Order> orders = orderService.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (orders == null) {
			headers.add(MESSAGE, ORDERS_NOT_FOUND);
			return new ResponseEntity<List<Order>>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(orders, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/orders/market/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getOrderByMarket(@PathVariable("id") int id) {
		Market market = marketService.findOne(id);
		List<Order> orders = orderService.findByMarket(market);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (orders == null) {
			headers.add(MESSAGE, ORDERS_NOT_FOUND);
			return new ResponseEntity<List<Order>>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(orders, headers, HttpStatus.OK);
	}

}
