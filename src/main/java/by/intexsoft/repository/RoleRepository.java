package by.intexsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.entity.Role;

/**
 * A repository that extracts from the database the roles that determine the
 * level of access to the components.
 * 
 * @see {@link JpaRepository},{@link AbstractEntityRepository}
 */
public interface RoleRepository extends AbstractEntityRepository<Role> {
	/**
	 * Sends a query to the database to search for roles with the name passed in the
	 * parameters.
	 * 
	 * @return {@link Role} returns a role with the name passed in the parameters.
	 * @see {@link Role}
	 * 
	 */
	Role findByName(String name);
}
