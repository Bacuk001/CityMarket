package by.intexsoft.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	Product findByName(String nameProduct);

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
	List<Product> findProductDistinctByStocksAndCategory(Stock stock, Category category);

	/**
	 * Counts the number of products in the stock in the category.
	 * 
	 * @see {@link Stock}, {@link Category}
	 */
	Integer countProductDistinctByStocksAndCategory(Stock stock, Category category);

	/**
	 * Counts the number of products in the market in the category.
	 * 
	 * @see {@link Stock}, {@link Category}
	 */
	Integer countByMarketsAndCategory(Market market, Category category);

	/**
	 * The repository method generates a query in the database to obtain information
	 * about the products contained in the repository in a specific category. The
	 * method takes two parameters. The first is the category in which the product
	 * is located, the second storage from which you need to take the products.
	 * 
	 * @see {@link Category}, {@link Stock}
	 */
	List<Product> findProductDistinctByCategoryAndStocksIn(Category category, List<Stock> stocks);

	/**
	 * A repository that manages the essence of products in a data buffer. Forms a
	 * query into the database to retrieve products that correspond to the category
	 * and are stock in warehouses. Only the number of records that is configured in
	 * the page is {@link Pageable}.
	 * 
	 * @see {@link Product}, {@link Pageable}, {@link Category}
	 */
	List<Product> findProductDistinctByCategoryAndStocksIn(Category category, List<Stock> stocks, Pageable pageable);

	/**
	 * The method generates a request to receive products contained in the category
	 * and in warehouses that are transmitted as input parameters. Provided that the
	 * product name will contain the characters passed in the string parameter.
	 * 
	 * @param category
	 *            - the category in which the goods are located.
	 * @param name
	 *            - part of the characters that are contained in the product name.
	 * @param stocks
	 *            -list of warehouses in which to search for products.
	 */
	List<Product> findProductDistinctByCategoryAndNameContainingAndStocksIn(Category category, String name,
			List<Stock> stocks, Sort sort);

	/**
	 * A repository that manages the essence of products in a data buffer. Forms a
	 * query into the database to retrieve the number of products that correspond to
	 * the category and are stored in warehouses.
	 * 
	 * @see {@link Product}, {@link Category}.
	 */
	int countProductDistinctByCategoryAndStocksIn(Category category, List<Stock> stocks);
}
