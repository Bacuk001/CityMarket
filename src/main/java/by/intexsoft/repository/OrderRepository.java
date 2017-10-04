package by.intexsoft.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.entity.Market;
import by.intexsoft.entity.Order;

/**
 * The centralized storage interface. to interact with the database table Order.
 * 
 * @see {@link Order},{@link JpaRepository}
 */
public interface OrderRepository extends AbstractEntityRepository<Order> {
	/**
	 * The method returns from the database the orders that the user ordered with
	 * the name transferred in the parameters.
	 */
	List<Order> findByNameUser(String userName);

	/**
	 * Search the repository of all orders issued in the store.
	 */
	List<Order> findByMarket(Market market);
}
