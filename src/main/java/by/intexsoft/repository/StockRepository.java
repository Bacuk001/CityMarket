package by.intexsoft.repository;

import java.util.List;

import by.intexsoft.entity.Market;
import by.intexsoft.entity.Stock;

/**
 * Repository for working with the warehouse database.
 * 
 * @see {@link AbstractEntityRepository}
 */
public interface StockRepository extends AbstractEntityRepository<Stock> {
	/**
	 * Search the database of the repository by its name.
	 * 
	 * @see {@link Stock}
	 */
	Stock findByName(String name);

	/**
	 * A method that forms a query in a database to retrieve all the warehouses from
	 * which the market receives the products.
	 * 
	 * @see {@link Market}
	 */
	List<Stock> findByMarkets(Market market);
}
