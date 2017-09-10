package by.intexoft.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The object of this class contains information about the product, as well as
 * links it to the repositories.
 * 
 * @see {@link AbstractPersistable}
 */
@Entity
@Table
public class Product extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -242178912328689444L;
	private static final String TABLE_NAME = "market_product";
	/**
	 * The field contains the name of the category field.
	 */
	public static final String CATEGORY_PROPERTY_NAME = "category";
	/**
	 * The field contains the name of the storage field.
	 */
	public static final String STOCK_PROPERTY_NAME = "stocks";

	/**
	 * The field contains the name of the market field.
	 */
	public static final String MARKET_PROPERTY_NAME = "markets";

	/**
	 * The field contains the name of the field in the orders.
	 */
	public static final String ORDER_PROPERTY_NAME = "order";
	/**
	 * The product's name.
	 */
	@Column
	public String name;

	/**
	 * To which group this product belongs.
	 * 
	 * @see {@link Category}
	 */
	@ManyToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn
	public Category category;

	/**
	 * The storage in which the product is stored.
	 * 
	 * @see {@link Stock}
	 */
	@ManyToMany(mappedBy = Stock.PRODUCT_PROPERTY_NAME)
	public List<Stock> stocks;

	/**
	 * Product description.
	 * 
	 * @see {@link Description}
	 */
	@OneToMany(fetch = LAZY, mappedBy = Description.PRODUCT_PROPERTY_NAME, cascade = ALL)
	public Description description;

	/**
	 * Field storing information in which orders this product.
	 */
	@ManyToMany(mappedBy = Order.PRODUCT_PROPERTY_NAME)
	public List<Order> order;

	/**
	 * Stores in which this product is sold.
	 */
	@ManyToMany
	@JoinTable(name = TABLE_NAME, joinColumns = {
			@JoinColumn(name = Market.PRODUCT_PROPERTY_NAME) }, inverseJoinColumns = {
					@JoinColumn(name = MARKET_PROPERTY_NAME) })
	public List<Market> markets;
}
