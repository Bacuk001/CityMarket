package by.intexoft.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The object of this class retains the characteristic of the goods.
 * 
 * @see {@link AbstractPersistable}
 */
@Entity
@Table
public class Description extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -6236253033618810834L;
	public static final String PRODUCT_PROPERTY_NAME = "product";
	/**
	 * Name of the characteristic.
	 */
	@Column
	public String name;
	/**
	 * Characteristic value.
	 */
	@Column
	public String value;

	/**
	 * The product belongs to the characteristic.
	 */
	@ManyToOne(fetch = LAZY, cascade = ALL)
	public Product product;
}
