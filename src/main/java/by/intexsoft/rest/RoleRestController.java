package by.intexsoft.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import by.intexsoft.entity.Role;
import by.intexsoft.repository.RoleRepository;
import by.intexsoft.service.IRoleService;
import by.intexsoft.service.impl.RoleService;

/**
 * The controller receives processing requests, retrieves role information in
 * the application, and roles are responsible for accessing the application's
 * application. By copying all the information for the response, the controller
 * creates a response and sends it to the user.
 * 
 * @see {@link RestController}, {@link RoleService}, {@link Role},
 *      {@link RoleRepository}
 */
@RestController
public class RoleRestController {
	private static final String APPLICATION_JSON = "application/json; charset=UTF-8";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String FIND_ROLES = "Find all roles from database.";
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleRestController.class);
	private IRoleService roleService;

	@Autowired
	public RoleRestController(IRoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * Controller processing requests for all roles.
	 */
	@RequestMapping(value = "/roles")
	public ResponseEntity<List<Role>> getRoles() {
		LOGGER.info(FIND_ROLES);
		List<Role> roles = roleService.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		return new ResponseEntity<List<Role>>(roles, headers, HttpStatus.OK);
	}
}
