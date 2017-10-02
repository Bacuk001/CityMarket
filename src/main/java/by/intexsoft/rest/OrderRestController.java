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
import by.intexsoft.entity.Order;
import by.intexsoft.repository.OrderRepository;
import by.intexsoft.service.MarketService;
import by.intexsoft.service.OrderService;

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
	private static final String ORDER_NOT_SAVE = "Order do not save.";
	private static final String SAVE_ORDER = "Save order to database.";
	private static final String GET_ORDER_BY_MARKET = "Get order by market.";
	private static final String FIND_ALL_ORDER = "Find all order.";
	private static final String APPLICATION = "application/json; charset=UTF-8";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String FIND_ORDER = "Find order by id.";
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

	/**
	 * The controller method that processes requests for ordering by id.
	 */
	@RequestMapping(name = "/order/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> getById(@PathVariable("id") int id) {
		LOGGER.info(FIND_ORDER);
		Order order = orderService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION);
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
		LOGGER.info(FIND_ALL_ORDER);
		List<Order> orders = orderService.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION);
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
		LOGGER.info(GET_ORDER_BY_MARKET);
		Market market = marketService.findOne(id);
		List<Order> orders = orderService.findByMarket(market);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION);
		if (orders == null) {
			headers.add(MESSAGE, ORDERS_NOT_FOUND);
			return new ResponseEntity<List<Order>>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(orders, headers, HttpStatus.OK);
	}

	/**
	 * The controller receives an order in the request and passes it to the service
	 * for saving in the database. If the save was successful, the controller method
	 * sends the saved object, with the status successfully. If the object is not
	 * saved in the database, then the response will be with the server error
	 * status.
	 * 
	 * @see {@link Order}, {@link OrderService}, {@link Order}
	 */
	@RequestMapping(value = "/order/save", method = RequestMethod.POST)
	public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
		LOGGER.info(SAVE_ORDER);
		order = orderService.save(order);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION);
		if (order == null) {
			headers.add(MESSAGE, ORDER_NOT_SAVE);
			return new ResponseEntity<Order>(order, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Order>(order, headers, HttpStatus.OK);
	}

	/**
	 * The controller receives an order in the request and passes it to the service
	 * for storage in a database linked to the market. If the save was successful,
	 * the controller method sends the saved object, with the status successfully.
	 * If the object is not saved in the database, then the response will be with
	 * the server error status.
	 * 
	 * @see {@link Order}, {@link OrderService}, {@link Order}
	 */
	@RequestMapping(value = "/order/save/product/{idProduct}/market/{idMarket}", method = RequestMethod.POST)
	public ResponseEntity<Order> saveOrdeInMarket(@RequestBody Order order, @PathVariable("idProduct") int idProduct,
			@PathVariable("idMarket") int idMarket) {
		LOGGER.info(SAVE_ORDER);
		order = orderService.saveByMarket(order, idMarket, idProduct);
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION);
		if (order == null) {
			headers.add(MESSAGE, ORDER_NOT_SAVE);
			return new ResponseEntity<Order>(order, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Order>(order, headers, HttpStatus.OK);
	}

}
