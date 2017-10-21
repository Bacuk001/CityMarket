package by.intexsoft.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import by.intexsoft.entity.Price;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;

/**
 * Interface on the basis of which the object will be created, to manipulate the
 * object price in the database.
 * 
 * @see {@link JpaRepository}, {@link Price} , {@link AbstractEntityRepository}
 */
public interface PriceRepository extends AbstractEntityRepository<Price> {
	/**
	 * To obtain the price for the product, you must transfer the product to which
	 * the price is needed and the list of warehouses from which prices must be
	 * extracted.
	 * 
	 * @see {@link Product}, {@link Stock}
	 */
	List<Price> findPriceDistinctByProductAndStockIn(Product product, List<Stock> stocks);

	/**
	 * The repository method generates a query into the database to obtain a list of
	 * product prices in the warehouse.
	 * 
	 * @see {@link Product}, {@link Stock}
	 */
	List<Price> findByProductAndStock(Product product, Stock stock);

	/**
	 * The method takes a list of list {@link Product} and a list {@link Stock} and
	 * forms a request for prices to all transferred products located in warehouses.
	 * Gets the data from the repository and sends it to the user.
	 * 
	 * @see {@link Pageable}
	 */
	List<Price> findPriceDistinctByProductInAndStockInAndInStockNotLike(List<Product> products, List<Stock> stocks,
			Integer inStock, Pageable pageable);

	/**
	 * The method generates a request for the number of price records for the list
	 * of {@link Product} in the {@link Stock} that are transferred in the
	 * parameters. Products that are not in stock are excluded.
	 * 
	 */
	int countPriceDistinctByProductInAndStockInAndInStockNotLike(List<Product> products, List<Stock> stocks,
			Integer inStock);
}
