package by.intexsoft.entity;

import static javax.persistence.FetchType.LAZY;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A class is an instance of a repository. which contains information about the
 * location of the warehouse and a description of the type of activity of the
 * warehouse.
 * 
 * @see {@link AbstractPersistable}
 */
@Entity
@Table
public class Stock extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = 7845369286378735950L;
	private static final String TABLE_NAME_MARKET = "market_stock";
	private static final String TABLE_NAME_PRODUCT = "product_stock";
	/**
	 * The name of the list of products that are stored in the warehouse.
	 */
	public static final String PRODUCT_PROPERTY_NAME = "product";
	/**
	 * Field containing the name of the field that associates the store with market.
	 */
	public static final String MARKET_PROPERTY_NAME = "markets";
	/**
	 * Name of product store.
	 */
	@Column
	public String name;
	/**
	 * The address on which the product store is located.
	 */
	@Column
	public String address;
	/**
	 * The field contains information describing the storage.
	 */
	@Column
	public String about;
	/**
	 * The field contains all the stores that serve the storage.
	 * 
	 * @see {@link Market}
	 */
    @JsonIgnore
	@ManyToMany
	@JoinTable(name = TABLE_NAME_MARKET, joinColumns = {
			@JoinColumn(name = Market.STOCK_PROPERTY_NAME) }, inverseJoinColumns = {
					@JoinColumn(name = MARKET_PROPERTY_NAME) })
	public List<Market> markets;
	/**
	 * List of users that support this store. {@link User}
	 * 
	 * @see {@link User}
	 */
     @JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = User.STOCK_PROPERTY_NAME)
	public List<User> user;

	/**
	 * List of products that are stored in the warehouse. {@link Product}
	 */
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = TABLE_NAME_PRODUCT, joinColumns = {
			@JoinColumn(name = Product.STOCK_PROPERTY_NAME) }, inverseJoinColumns = {
					@JoinColumn(name = PRODUCT_PROPERTY_NAME) })
	public List<Product> product;

	/**
	 * The prices of which are formed in this store. {@link Price}
	 */
	@JsonIgnore
	@OneToMany(fetch = LAZY, mappedBy = Price.STOCK_PROPERTY_NAME)
	public List<Price> prises;

}
