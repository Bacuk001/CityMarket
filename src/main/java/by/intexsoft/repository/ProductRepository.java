package by.intexsoft.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
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
	 * Get all the products in the stock.
	 * 
	 * @see {@link Stock}
	 */
	List<Product> findByStocks(Stock stock);

	/**
	 * Get all the products in the market.
	 * 
	 * @see {@link Market}
	 */
	List<Product> findByMarkets(Market market);

	/**
	 * Get all the products in the market and category.
	 * 
	 * @see {@link Market}, {@link Category}
	 */
	List<Product> findByMarketsAndCategory(Market market, Category category);

	/**
	 * Get all the products in the stock and category.
	 * 
	 * @see {@link Market}, {@link Category}
	 */
	List<Product> findByStocksAndCategory(Stock stock, Category category);

	/**
	 * Counts the number of products in the stock in the category.
	 * 
	 * @see {@link Stock}, {@link Category}
	 */
	Integer countByStocksAndCategory(Stock stock, Category category);

	/**
	 * Counts the number of products in the market in the category.
	 * 
	 * @see {@link Stock}, {@link Category}
	 */
	Integer countByMarketsAndCategory(Market market, Category category);
}
