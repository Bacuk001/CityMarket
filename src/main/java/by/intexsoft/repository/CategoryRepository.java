package by.intexsoft.repository;

import java.util.List;

import by.intexsoft.entity.Category;

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
}
