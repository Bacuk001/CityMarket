package by.intexsoft.repository;

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
}
