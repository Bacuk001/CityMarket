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

import by.intexsoft.configuration.service.MarketService;
import by.intexsoft.configuration.service.OrderService;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Order;
import by.intexsoft.entity.Product;
import by.intexsoft.repository.MarketRepository;
import by.intexsoft.repository.OrderRepository;
import by.intexsoft.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrderRestControllerTest {
	private static final String MESSAGE = "Message";
	private static final String ORDERS_NOT_FOUND = "Orders not found.";
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private MarketRepository marketRepository;
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	OrderService orderService = new OrderService(marketRepository, productRepository, orderRepository);
	@InjectMocks
	MarketService marketService = new MarketService(marketRepository);
	@InjectMocks
	OrderRestController orderRestController = new OrderRestController(orderService, marketService);

	@Test
	public void getByIdTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		Order order = new Order();
		when(orderRepository.findOne(1)).thenReturn(order);
		when(orderRepository.findOne(0)).thenReturn(null);
		ResponseEntity<Order> response = new ResponseEntity<Order>(order, headers, HttpStatus.OK);
		assertEquals(orderRestController.getById(1), response);
		headers.add(MESSAGE, "Order not found.");
		response = new ResponseEntity<Order>(headers, HttpStatus.NOT_FOUND);
		assertEquals(orderRestController.getById(0), response);
	}

	@Test
	public void getAllTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		List<Order> orders = new ArrayList<>();
		when(orderRepository.findAll()).thenReturn(orders);
		ResponseEntity<List<Order>> response = new ResponseEntity<List<Order>>(orders, headers, HttpStatus.OK);
		assertEquals(orderRestController.getAll(), response);
		when(orderRepository.findAll()).thenReturn(null);
		headers.add(MESSAGE, ORDERS_NOT_FOUND);
		response = new ResponseEntity<List<Order>>(headers, HttpStatus.NOT_FOUND);
		assertEquals(orderRestController.getAll(), response);
	}

	@Test
	public void getOrderByMarketTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		Market market = new Market();
		List<Order> orders = new ArrayList<>();
		when(marketRepository.findOne(0)).thenReturn(market);
		when(orderService.findByMarket(market)).thenReturn(orders);
		ResponseEntity<List<Order>> response = new ResponseEntity<List<Order>>(orders, headers, HttpStatus.OK);
		assertEquals(orderRestController.getOrderByMarket(0), response);
		headers.add(MESSAGE, ORDERS_NOT_FOUND);
		when(orderService.findByMarket(market)).thenReturn(null);
		response = new ResponseEntity<List<Order>>(headers, HttpStatus.NOT_FOUND);
		assertEquals(orderRestController.getOrderByMarket(0), response);
	}

	@Test
	public void saveOrderTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		Order order = new Order();
		when(orderRepository.save(order)).thenReturn(order);
		ResponseEntity<Order> response = new ResponseEntity<Order>(order, headers, HttpStatus.OK);
		assertEquals(orderRestController.saveOrder(order), response);
		when(orderRepository.save(order)).thenReturn(null);
		headers.add(MESSAGE, "Order do not save.");
		response = new ResponseEntity<Order>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(orderRestController.saveOrder(order), response);
	}

	@Test
	public void saveOrderInMarket() {
		Order order = new Order();
		when(marketRepository.findOne(1)).thenReturn(new Market());
		when(productRepository.findOne(1)).thenReturn(new Product());
		when(orderRepository.save(order)).thenReturn(order);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		ResponseEntity<Order> response = new ResponseEntity<Order>(order, headers, HttpStatus.OK);
		assertEquals(orderRestController.saveOrdeInMarket(order, 1, 1), response);
		when(orderRepository.save(order)).thenReturn(null);
		headers.add(MESSAGE, "Order do not save.");
		response = new ResponseEntity<Order>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(orderRestController.saveOrdeInMarket(order, 1, 1), response);
	}
}
