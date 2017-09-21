package by.intexsoft.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The object of this class contains information about the role of the user of
 * the application. The role determines which components will be available to
 * that user.
 */
@Entity
@Table
public class Role extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -6243444405068572906L;
	/**
	 * The name of the field that binds this role to the user.
	 */
	public static final String USER_PROPERTY_NAME = "users";
	/**
	 * Whose role name will be granted access.
	 */
	@Column
	public String name;
	/**
	 * The field that stores the user to whom this role belongs.
	 */
	@JsonIgnore
	@ManyToMany(mappedBy = User.ROLE_PROPERTY_NAME)
	public List<User> users;

}
