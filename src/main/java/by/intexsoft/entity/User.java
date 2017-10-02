package by.intexsoft.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * The object of this class will be the user of our application, which will be
 * granted rights to access the components or access to other components.
 * 
 * @see {@link AbstractPersistable}
 */
@Entity
@Table
public class User extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -8490985203125626735L;
	private static final String TABLE_NAME = "roles_users";
	/***
	 * The name of the field in which information is stored to which warehouse this
	 * user has access.
	 */
	public static final String STOCK_PROPERTY_NAME = "stock";
	/**
	 * The field contains the name of the field in which the user information about
	 * the roles is stored.
	 */
	public static final String ROLE_PROPERTY_NAME = "roles";

	/**
	 * The field contains the name of the field in which the user information about
	 * the market.
	 */
	public static final String MERKET_PROPERTY_NAME = "market";
	/**
	 * User name.
	 */
	@Column
	public String name;

	/**
	 * User password.
	 */
	@Column
	@JsonProperty(access = Access.WRITE_ONLY)
	public String password;

	/**
	 * The field stores information to which store it is attached and serves.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	public Market market;

	/**
	 * The field stores information about which warehouse serves.
	 * 
	 * @see {@link Stock}
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	public Stock stock;

	/**
	 * The list of roles that the user has to access certain components.
	 * 
	 * @see {@link Role}
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = TABLE_NAME, joinColumns = { @JoinColumn(name = Role.USER_PROPERTY_NAME) }, inverseJoinColumns = {
			@JoinColumn(name = ROLE_PROPERTY_NAME) })
	public List<Role> roles;
}
