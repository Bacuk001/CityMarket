package by.intexsoft.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The object of this class stores information about the order left on the
 * market.
 */
@Entity
@Table(name = "in_order")
public class Order extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -1954392259204189439L;
	private static final String TABLE_NAME = "order_product";
	/**
	 * The name of the field that contains the store in which the order was formed.
	 */
	public static final String MARCET_PROPERTY_NAME = "market";
	/**
	 * The field name that contains a list of products that have been ordered in the
	 * store.
	 */
	public static final String PRODUCT_PROPERTY_NAME = "product";
	/**
	 * User name.
	 */
	@Column
	public String nameUser;
	/**
	 * Contact information for the user.
	 */
	@Column
	public String contacts;
	/**
	 * The address where the order will be placed.
	 */
	@Column
	public String address;
	/**
	 * Order status.
	 */
	@Column
	public String status;

	/**
	 * The shop in which the order was made.
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn
	public Market market;

	/**
	 * Products that have been ordered in the store.
	 */
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = TABLE_NAME, joinColumns = {
			@JoinColumn(name = Product.ORDER_PROPERTY_NAME) }, inverseJoinColumns = {
					@JoinColumn(name = PRODUCT_PROPERTY_NAME) })
	public List<Product> product;
}
