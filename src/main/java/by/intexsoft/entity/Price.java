package by.intexsoft.entity;

import static javax.persistence.FetchType.EAGER;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Object for managing data in a database that stores information about the
 * prices of goods. Goods are in reserve and the balance of goods in the
 * warehouse.
 */
@Entity
@Table
public class Price extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -7214125296547747238L;
	/**
	 * The name of the product field.
	 */
	public static final String PRODUCT_PROPERTY_NAME = "product";
	/**
	 * The name of the product stock.
	 */
	public static final String STOCK_PROPERTY_NAME = "stock";

	/**
	 * The price of the product.
	 */
	@Column
	public Integer price;

	/**
	 * Quantity in stock.
	 */
	@Column
	public Integer inStock;

	/**
	 * The product is in reserve.
	 */
	@Column
	public Integer inReserv;

	/**
	 * Product information. {@link Product}
	 */
	@ManyToOne(fetch = EAGER)
	@JoinColumn
	public Product product;

	/**
	 * The warehouse to which the price relates. {@link Stock}
	 */
	@ManyToOne(fetch = EAGER)
	@JoinColumn
	public Stock stock;

}
