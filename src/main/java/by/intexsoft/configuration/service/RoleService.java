package by.intexsoft.configuration.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.intexsoft.entity.Role;
import by.intexsoft.repository.RoleRepository;

@Service
public class RoleService {
	private RoleRepository roleRepository;

	@Autowired
	RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role findOne(int id) {
		return roleRepository.findOne(id);
	}

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
