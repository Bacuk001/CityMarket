package by.intexsoft.configuration.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import by.intexsoft.entity.Role;
import by.intexsoft.entity.User;
import by.intexsoft.repository.RoleRepository;

/**
 * Service that interacts with the repository of roles that are responsible for
 * access rights.
 *
 * @see {@link Role}, {@link User}, {@link JpaRepository}
 *      ,{@link RoleRepository}.
 */
@Service
public class RoleService {
	private RoleRepository roleRepository;

	RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	/**
	 * The service method that takes the name of the role, accesses the repository,
	 * and receives an object with the name of the role corresponding to the
	 * transferred parameters. and returns the service to the user.
	 */
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	/**
	 * A service method that requests from the repository all the roles that are
	 * present in the database.
	 */
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	/**
	 * A service method that requests from the repository a role whose id matches
	 * the value specified in the parameters.
	 */
	public Role findOne(int id) {
		return roleRepository.findOne(id);
	}

	/**
	 * A service method that passes a role object to the repository for saving to
	 * the database.
	 */
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	/**
	 * Deletes the role by id.
	 */
	public void delete(int id) {
		roleRepository.delete(id);
	}
}
