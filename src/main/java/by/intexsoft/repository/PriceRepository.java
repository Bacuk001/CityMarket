package by.intexsoft.repository;

import java.util.List;

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
    
    Price findByProductAndStock(Product product,Stock stock);
}
