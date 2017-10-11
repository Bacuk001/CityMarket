package by.intexsoft.configuration.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import by.intexsoft.entity.User;
import by.intexsoft.rest.AuthorizationRestController;
import by.intexsoft.security.TokenAuthenticationService;
import by.intexsoft.service.impl.UserService;


@RunWith(MockitoJUnitRunner.class)
public class AuthorizationRestControllerTest {
	private static final String MESSAGE = "Message";
	@Mock 
	UserService userService;

	@Mock
	TokenAuthenticationService authenticationService;
	
	@InjectMocks
	AuthorizationRestController authorizationRestController = new AuthorizationRestController(userService,
			authenticationService);

	@Test
	public void testAuthorizationRestController() {
		User user = new User();
		user.name="sam";
		user.password="sam";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		headers.add("token", "");
		ResponseEntity<User> resp = new ResponseEntity<User>(user,headers,HttpStatus.OK);
		when(userService.findByNameAndPassword("sam","sam")).thenReturn(user);
		when(userService.findByNameAndPassword("","")).thenReturn(null);
		when(authenticationService.addAuthentication("sam")).thenReturn("");
		assertEquals(authorizationRestController.getTokenAutentification(user), resp);
		user.name="";
		user.password="";
		HttpHeaders headersNotFund = new HttpHeaders();
		headersNotFund.add("Content-Type", "application/json; charset=UTF-8");
		headersNotFund.add(MESSAGE, "The user is not registered in the system.");
		resp = new ResponseEntity<User>(headersNotFund, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		assertEquals(authorizationRestController.getTokenAutentification(user), resp);
	}
}
