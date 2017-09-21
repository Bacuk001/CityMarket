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

import by.intexsoft.configuration.service.UserService;
import by.intexsoft.entity.User;
import by.intexsoft.repository.UserRepository;

/**
 * The controller processes requests for receiving, adding and editing users of
 * the application.
 * 
 * @see {@link User} , {@link UserService}, {@link RestController},
 *      {@link UserRepository}
 */
@RestController
public class UserRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);
	private static final String USER_NOT_FOUND = "User not found.";
	private static final String MESSAGE = "Message";
	private UserService userService;

	@Autowired
	UserRestController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Controller processing requests for the user on his id.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		LOGGER.info("Find user by id in database.");
		User user = userService.findOne(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (user == null) {
			headers.add(MESSAGE, USER_NOT_FOUND);
			return new ResponseEntity<User>(user, headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}

	/**
	 * The controller processes requests for all users in the system.
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		LOGGER.info("Find all users.");
		List<User> users = userService.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (users == null) {
			headers.add(MESSAGE, USER_NOT_FOUND);
			return new ResponseEntity<List<User>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, headers, HttpStatus.OK);
	}

	/**
	 * The controller processes requests for user retention.
	 */
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public ResponseEntity<User> name(@RequestBody User user) {
		user = userService.save(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (user == null) {
			headers.add(MESSAGE, "Do not save.");
			return new ResponseEntity<User>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}

}
