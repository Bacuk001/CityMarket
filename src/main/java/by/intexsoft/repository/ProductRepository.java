package by.intexsoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.entity.Category;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;

/**
 * Repository for obtaining information about the products with which the
 * organization operates.
 * 
 * @see {@link AbstractEntityRepository}, {@link JpaRepository}.
 *
 */
public interface ProductRepository extends AbstractEntityRepository<Product> {

	/**
	 * Extract the name specified in the input parameters from the product database.
	 * 
	 * @see {@link Product}
	 */
	List<Product> findByName(String nameProduct);

	/**
	 * Search all products in category.
	 * 
	 * @see {@link Category}
	 */
	List<Product> findByCategory(Category nameCategory);

	/**
	 * Get all the products in stock.
	 * 
	 * @see {@link Stock}
	 */
	List<Product> findByStock(Stock stock);
}
