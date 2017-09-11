package by.intexsoft.repository;

import java.util.List;

import by.intexsoft.entity.Market;
import by.intexsoft.entity.Role;
import by.intexsoft.entity.Stock;
import by.intexsoft.entity.User;

/**
 * Repository to work with the user database.
 * 
 * @see {@link AbstractEntityRepository}
 */
public interface UserRepository extends AbstractEntityRepository<User> {

	/**
	 * Search in the user database by the name transferred in the parameters.
	 * 
	 * @param name
	 *            user name.
	 * @return {@link User}
	 */
	User findByName(String name);

	/**
	 * Search for users who serve to the market.
	 * 
	 * @param market
	 *            name market.
	 * @return {@link List}<{@link User}>
	 */
	List<User> findByMarket(Market market);

	/**
	 * Search for users who serve the storage.
	 * 
	 * @param stock
	 *            store name
	 * @return {@link List}<{@link User}>
	 */
	List<User> findByStock(Stock stock);

	/**
	 * Search for users who have roles transferred in the settings.
	 * 
	 * @param role
	 *            the name of the role.
	 * @return {@link List} <{@link User}>
	 */
	List<User> findByRole(Role role);

	/**
	 * The repository retrieves from the user database whose name and password match
	 * the parameters passed.
	 */
	User findByNameAndPassword(String name, String password);
}
