package by.intexsoft.repository;

import java.util.List;

import by.intexsoft.entity.Category;
import by.intexsoft.entity.Product;

/**
 * Repository for working with the database entity that is responsible for the
 * unit of categories.
 */
public interface CategoryRepository extends AbstractEntityRepository<Category> {
	/**
	 * The method that sends the query to the database with the
	 * {@linkplain Category} name, receives the data and returns a
	 * {@link List}<{@link Category}> of product categories.
	 */
	Category findByName(String name);

	/**
	 * The method generates a query into the database to extract the category in
	 * which the product is located. {@link Product}
	 */
	List<Category> findByProducts(List<Product> products);
}
