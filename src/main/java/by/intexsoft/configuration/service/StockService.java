package by.intexsoft.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.entity.Stock;
import by.intexsoft.repository.StockRepository;
import by.intexsoft.repository.UserRepository;

/**
 * Service for working with the repository, which processes information about
 * warehouses. Using this class, you can receive, store, modify information
 * about the repositories.
 * 
 * @see {@link User}, {@link UserRepository}
 */
@Service
public class StockService {
	private StockRepository stockRepository;

	@Autowired
	StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	/**
	 * Gets the stock from the repository by its name.
	 * 
	 * @param nameStock
	 *            store name.
	 * @return {@link Stock}
	 */
	public Stock findByName(String nameStock) {
		return stockRepository.findByName(nameStock);
	}

	/**
	 * Saving a new storage.
	 * 
	 * @param stock
	 *            instance @link {@link Stock}
	 */
	public Stock save(Stock stock) {
		return stockRepository.save(stock);
	}

	/**
	 * Returns all stocks from the repository.
	 * 
	 * @return {@link List} <{@link Stock}>
	 */
	public List<Stock> findAll() {
		return stockRepository.findAll();
	}

	/**
	 * Returns the store by its id.
	 */
	public Stock findOne(int id) {
		return stockRepository.findOne(id);
	}

	/**
	 * Deletes the stock by id.
	 */
	public void delete(int id) {
		stockRepository.delete(id);
	}

}
