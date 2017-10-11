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

import by.intexsoft.entity.User;
import by.intexsoft.repository.UserRepository;
import by.intexsoft.rest.UserRestController;
import by.intexsoft.service.IUserService;
import by.intexsoft.service.impl.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {
	private static final String USER_NOT_FOUND = "User not found.";
	private static final String MESSAGE = "Message";
	@Mock
	UserRepository userRepository;
	@InjectMocks
	IUserService userService = new UserService(userRepository);
	UserRestController userRestController = new UserRestController(userService);

	@Test
	public void getUserBtId() {
		User user = new User();
		when(userRepository.findOne(1)).thenReturn(user);
		when(userRepository.findOne(0)).thenReturn(null);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		ResponseEntity<User> response = new ResponseEntity<User>(user, headers, HttpStatus.OK);
		assertEquals(userRestController.getUserById(1), response);
		headers.add(MESSAGE, USER_NOT_FOUND);
		response = new ResponseEntity<User>(null, headers, HttpStatus.NO_CONTENT);
		assertEquals(userRestController.getUserById(0), response);
	}

	@Test
	public void getUsersTest() {
		when(userRepository.findAll()).thenReturn(new ArrayList<User>());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		ResponseEntity<List<User>> respons = new ResponseEntity<List<User>>(new ArrayList<User>(), headers,
				HttpStatus.OK);
		assertEquals(userRestController.getUsers(), respons);
	}

	@Test
	public void saveTest() {
		User user = new User();
		when(userRepository.save(user)).thenReturn(user);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		ResponseEntity<User> response = new ResponseEntity<User>(user, headers, HttpStatus.OK);
		assertEquals(userRestController.save(user), response);
		when(userRepository.save(user)).thenReturn(null);
		headers.add(MESSAGE, "Do not save.");
		response = new ResponseEntity<User>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(userRestController.save(user), response);
	}

}
