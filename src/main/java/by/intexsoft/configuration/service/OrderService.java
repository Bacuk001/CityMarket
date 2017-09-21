package by.intexsoft.configuration.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Order;
import by.intexsoft.entity.User;
import by.intexsoft.repository.OrderRepository;

/**
 * Service processing information about orders from the store. With the help of
 * the service, you can extract data from the repository, save data, and delete
 * data. Receive data related to a particular store.
 * 
 * @see {@link Order}, {@link OrderRepository}, {@link JpaRepository}
 */
@Service
public class OrderService {
	private OrderRepository orderRepository;

	@Autowired
	OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	/**
	 * The service method that receives the user name that sent the order to the
	 * server. and returns the orders that the user has issued.
	 * 
	 * @return {@link List}<{@link User}>
	 */
	public List<Order> findByNameUser(String nameUser) {
		return orderRepository.findByNameUser(nameUser);
	}

	/**
	 * A service method that sends an order to the repository for storage.
	 */
 
	public Order save(Order order) {
        System.out.println("-------------------------"+order.market.name+"------------------------------");
		return orderRepository.save(order);
	}

	/**
	 * A service method that sends the object id to the repository that you want to
	 * find in the database. After receiving it sends the user a class.
	 * 
	 * @return {@link Order}
	 */
	public Order findOne(int id) {
		return orderRepository.findOne(id);
	}

	/**
	 * The method requests all orders from the repository and returns them to the
	 * user.
	 * 
	 * @return {@link List}<@link {@link Order}>
	 */
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	/**
	 * Deletes the order by id.
	 */
	public void delete(int id) {
		orderRepository.delete(id);
	}

	/**
	 * The service method accepts the market object and requests from the repository
	 * all the orders issued in the market.
	 * 
	 * @return {@link List}<@link {@link Order}>
	 */
	public List<Order> findByMarket(Market market) {
		return orderRepository.findByMarket(market);
	}

}
