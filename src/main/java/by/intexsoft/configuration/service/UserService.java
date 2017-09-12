package by.intexsoft.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class UserService {
	private UserRepository userRepository;

	@Autowired
	UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Returns from the user's repository by its name.
	 * 
	 * @param nameUser
	 *            user name.
	 * @return {@link User}.
	 */
	public User findByName(String nameUser) {
		return userRepository.findByName(nameUser);
	}

	/**
	 * Returns the users who are assigned to manage the market.
	 * 
	 * @param market
	 *            {@link Market}
	 * @return {@link List}<{@link User}>
	 * @see {@link User}
	 */
	public List<User> findByMarket(Market market) {
		return userRepository.findByMarket(market);
	}

	/**
	 * Returns the users who are assigned to manage the store.
	 * 
	 * @param stock
	 *            instance {@link Stock}
	 * @return {@link List}<{@link Stock}>
	 */
	public List<User> findByStock(Stock stock) {
		return userRepository.findByStock(stock);
	}

	/**
	 * Returns users who have roles specified in the passed parameters.
	 * 
	 * @param role
	 *            instance {@link Role}
	 * @return {@link List}<{@link Role}>
	 */
	public List<User> findByRole(Role role) {
		return userRepository.findByRoles(role);
	}

	/**
	 * Saving a new user.
	 * 
	 * @param user
	 *            instance {@link User}
	 * @return {@link User}
	 */
	public User save(User user) {
		return userRepository.save(user);
	}

	/**
	 * Receive all users.
	 * 
	 * @return {@link List}<{@link User}>
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Getting a user by id.
	 * 
	 * @return {@link User}
	 */
	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	/**
	 * Returns the user by his name and password.
	 * 
	 * @param name
	 *            user name;
	 * @param password
	 *            user password
	 * @return {@link User}
	 */
	public User findByNameAndPassword(String name, String password) {
		return userRepository.findByNameAndPassword(name, password);
	}

	/**
	 * Deletes the user by id.
	 */
	public void delete(int id) {
		userRepository.delete(id);
	}
}
