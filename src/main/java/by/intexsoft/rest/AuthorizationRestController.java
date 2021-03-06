package by.intexsoft.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.entity.User;
import by.intexsoft.security.TokenAuthenticationService;
import by.intexsoft.service.UserService;

/**
 * The controller is responsible for registering the user in the system. When
 * accessing the URL, the user transmits the data for registration. The
 * controller requests data from the service about the user. If the user exists
 * in the system, a token is returned to him.
 * 
 * @see {@link TokenAuthenticationService}, {@link RestController},
 *      {@link UserService}
 */
@RestController
public class AuthorizationRestController {
	private static final String USER_NOT_REGISTERED = "The user is not registered in the system.";
	private static final String START_UTHORIZATION_USER = "Start  uthorization user.";
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationRestController.class);
	private static final String MESSAGE = "Message";
	private TokenAuthenticationService authenticationService;
	private UserService userService;

	@Autowired
	public AuthorizationRestController(UserService userService, TokenAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
		this.userService = userService;
	}

	/**
	 * A service method that checks for user registration and returns a token.
	 */
	@RequestMapping(value = "/Application/login", method = RequestMethod.POST)
	public ResponseEntity<User> getTokenAutentification(@RequestBody User user) {
		LOGGER.info(START_UTHORIZATION_USER);
		user = userService.findByNameAndPassword(user.name, user.password);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (user == null) {
			headers.add(MESSAGE, USER_NOT_REGISTERED);
			return new ResponseEntity<User>(headers, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
		String token = authenticationService.addAuthentication(user.name);
		headers.add("token", token);
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}

}
