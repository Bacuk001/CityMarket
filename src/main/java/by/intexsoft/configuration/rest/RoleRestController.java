package by.intexsoft.configuration.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import by.intexsoft.configuration.service.RoleService;
import by.intexsoft.entity.Role;

@RestController
public class RoleRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleRestController.class);
	private static final String ROLE_NOT_FOUND = "role not found.";
	private static final String MESSAGE = "Message";
	private RoleService roleService;

	@Autowired
	RoleRestController(RoleService roleService) {
		this.roleService = roleService;
	}

	@RequestMapping(value = "/roles")
	public ResponseEntity<List<Role>> getRoles() {
		LOGGER.info("Find all roles from database.");
		List<Role> roles = roleService.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (roles == null) {
			headers.add(MESSAGE, ROLE_NOT_FOUND);
			return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Role>>(roles, headers, HttpStatus.OK);
	}
}
