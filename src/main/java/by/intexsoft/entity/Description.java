package by.intexsoft.entity;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(fetch = LAZY)
	public Product product;
}
