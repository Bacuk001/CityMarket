package by.intexoft.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * With the help of the class all goods are grouped into a certain group.
 * @see {@link Category}
 */
@Entity
@Table
public class Category extends AbstractPersistable<Integer> {
	private static final long serialVersionUID = -2448414635929993292L;
	/**
	 * Name of the group.
	 */
	@Column
	public String name;

	/**
	 * List of products in the group.
	 */
	@OneToMany(fetch = LAZY, mappedBy = Product.CATEGORY_PROPERTY_NAME, cascade = ALL)
	public List<Product> products;
}
