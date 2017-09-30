package by.intexsoft.configuration.service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Role;
import by.intexsoft.entity.Stock;
import by.intexsoft.entity.User;
import by.intexsoft.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock
	UserRepository userRepository;
	@Mock
	User user;

	@InjectMocks
	UserService userService = new UserService(userRepository);

	@Test
	public void testUserService() {
		String userName = "viktor";
		when(userService.findByName(userName)).thenReturn(user);
		assertEquals(userService.findByName(userName), user);
		when(userService.findOne(1)).thenReturn(user);
		assertEquals(userService.findOne(1), user);
		when(userService.findByMarket(new Market())).thenReturn(new ArrayList<User>());
		assertEquals(userService.findAll(), new ArrayList<User>());
		when(userService.findAll()).thenReturn(new ArrayList<User>());
		assertEquals(userService.findByMarket(new Market()), new ArrayList<User>());
		when(userService.findByStock(new Stock())).thenReturn(new ArrayList<User>());
		assertEquals(userService.findByStock(new Stock()), new ArrayList<User>());
		when(userService.findByRole(new Role())).thenReturn(new ArrayList<User>());
		assertEquals(userService.findByRole(new Role()), new ArrayList<User>());
		when(userService.save(user)).thenReturn(user);
		assertEquals(userService.save(user), user);
		when(userService.findByNameAndPassword("name", "password")).thenReturn(user);
		assertEquals(userService.findByNameAndPassword("name", "password"), user);
	}

}
