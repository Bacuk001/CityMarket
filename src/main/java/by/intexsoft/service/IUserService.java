package by.intexsoft.service;

import java.util.List;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Role;
import by.intexsoft.entity.Stock;
import by.intexsoft.entity.User;
import by.intexsoft.repository.UserRepository;

/**
 * A service for working with a repository that accesses information about
 * users. Using this class, you can receive, save, modify users.
 * 
 * @see {@link UserRepository}, {@link User}
 */
public interface IUserService {
	/**
	 * Returns from the user's repository by its name.
	 * 
	 * @param nameUser
	 *            user name.
	 * @return {@link User}.
	 */
	public User findByName(String nameUser);

	/**
	 * Returns the users who are assigned to manage the market.
	 * 
	 * @param market
	 *            {@link Market}
	 * @return {@link List}<{@link User}>
	 * @see {@link User}
	 */
	public List<User> findByMarket(Market market);

	/**
	 * Returns the users who are assigned to manage the store.
	 * 
	 * @param stock
	 *            instance {@link Stock}
	 * @return {@link List}<{@link Stock}>
	 */
	public List<User> findByStock(Stock stock);

	/**
	 * Returns users who have roles specified in the passed parameters.
	 * 
	 * @param role
	 *            instance {@link Role}
	 * @return {@link List}<{@link Role}>
	 */
	public List<User> findByRole(Role role);

	/**
	 * Saving a new user.
	 * 
	 * @param user
	 *            instance {@link User}
	 * @return {@link User}
	 */

	public User save(User user);

	/**
	 * Receive all users.
	 * 
	 * @return {@link List}<{@link User}>
	 */
	public List<User> findAll();

	/**
	 * Getting a user by id.
	 * 
	 * @return {@link User}
	 */
	public User findOne(int id);

	/**
	 * Returns the user by his name and password.
	 * 
	 * @param name
	 *            user name;
	 * @param password
	 *            user password
	 * @return {@link User}
	 */
	public User findByNameAndPassword(String name, String password);

	/**
	 * Deletes the user by id.
	 */
	public void delete(int id);

}
