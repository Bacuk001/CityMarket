package by.intexsoft.configuration.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.entity.Market;
import by.intexsoft.entity.Order;
import by.intexsoft.repository.OrderRepository;

@Service
public class OrderService {
	private OrderRepository orderRepository;

	@Autowired
	OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<Order> findByNameUser(String nameUser) {
		return orderRepository.findByNameUser(nameUser);
	}

	public Order save(Order order) {
		return orderRepository.save(order);
	}

	public Order findOne(int id) {
		return orderRepository.findOne(id);
	}

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	/**
	 * Deletes the order by id.
	 */
	public void delete(int id) {
		orderRepository.delete(id);
	}

	public List<Order> findByMarket(Market market) {
		return orderRepository.findByMarket(market);
	}

}
