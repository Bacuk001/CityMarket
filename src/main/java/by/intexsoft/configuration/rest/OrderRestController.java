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
import by.intexsoft.configuration.service.OrderService;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Order;
import by.intexsoft.repository.OrderRepository;

/**
 * A controller that processes requests for information about orders The
 * controller receives requests, processes the information, and returns the
 * responses to the user. The controller can receive the object and send it to
 * the {@link OrderService} repository, it can process the transfer control of
 * the {@link OrderService} to delete the order.
 * 
 * @see {@link RestController}, {@link OrderService}, {@link Order},
 *      {@link OrderRepository}
 */
@RestController
public class OrderRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderRestController.class);
	private static final String ORDERS_NOT_FOUND = "Orders not found.";
	private static final String MESSAGE = "Message";
	private OrderService orderService;
	private MarketService marketService;

	@Autowired
	OrderRestController(OrderService orderService, MarketService marketService) {
		this.orderService = orderService;
		this.marketService = marketService;
	}

	/**
	 * The controller method that processes requests for ordering by id.
	 */
	@RequestMapping(name = "/order/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> getById(@PathVariable("id") int id) {
		LOGGER.info("Find order by id.");
		Order order = orderService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (order == null) {
			headers.add(MESSAGE, "Order not found.");
			return new ResponseEntity<Order>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(order, headers, HttpStatus.OK);
	}

	/**
	 * The controller method that processes requests for all orders.
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getAll() {
		LOGGER.info("Find all order.");
		List<Order> orders = orderService.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (orders == null) {
			headers.add(MESSAGE, ORDERS_NOT_FOUND);
			return new ResponseEntity<List<Order>>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(orders, headers, HttpStatus.OK);
	}

	/**
	 * A controller method that processes requests for all orders in the store.
	 * 
	 * @see {@link Market}
	 */
	@RequestMapping(value = "/orders/market/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getOrderByMarket(@PathVariable("id") int id) {
		LOGGER.info("Get order by market.");
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

    @RequestMapping(value = "/order/save", method = RequestMethod.POST)
	public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
		LOGGER.info("Save order to database.");
		order = orderService.save(order);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (order == null) {
			headers.add(MESSAGE, "Order do not save.");
			return new ResponseEntity<Order>(order, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Order>(order, headers, HttpStatus.OK);
	}

}
