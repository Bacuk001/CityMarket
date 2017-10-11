package by.intexsoft.service;

import java.util.List;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Stock;
import by.intexsoft.entity.User;
import by.intexsoft.repository.MarketRepository;
import by.intexsoft.repository.UserRepository;

/**
 * Service for working with the repository, which processes information about
 * warehouses. Using this class, you can receive, store, modify information
 * about the repositories.
 * 
 * @see {@link User}, {@link UserRepository}
 */
public interface IStockService {
	/**
	 * Gets the stock from the repository by its name.
	 * 
	 * @param nameStock
	 *            store name.
	 * @return {@link Stock}
	 */
	public Stock findByName(String nameStock);

	/**
	 * Saving a new storage.
	 * 
	 * @param stock
	 *            instance @link {@link Stock}
	 */
	public Stock save(Stock stock);

	/**
	 * Returns all stocks from the repository.
	 * 
	 * @return {@link List} <{@link Stock}>
	 */
	public List<Stock> findAll();

	/**
	 * Returns the store by its id.
	 */
	public Stock findOne(int id);

	/**
	 * Deletes the stock by id.
	 */
	public void delete(int id);

	/**
	 * The service method returns the list of warehouses for which the warehouse is
	 * subscribed.
	 * 
	 * @see {@link Stock} , {@link MarketRepository}
	 */
	public List<Stock> finfByMasrket(Market market);

	/**
	 * A service method that removes all links from the database and creates new
	 * ones that are passed by the list in the input parameters.
	 * 
	 * @see {@link Stock}
	 */
	public List<Stock> signStockforMarket(List<Stock> stocks, int idMarket);
}
