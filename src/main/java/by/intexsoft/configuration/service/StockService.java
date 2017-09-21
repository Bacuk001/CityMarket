package by.intexsoft.configuration.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.MarketRepository;
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
	private MarketRepository marketRepository;

	@Autowired
	StockService(MarketRepository marketRepository, StockRepository stockRepository) {
		this.stockRepository = stockRepository;
		this.marketRepository = marketRepository;
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

	public List<Stock> finfByMasrket(Market market) {
		return stockRepository.findByMarkets(market);
	}

	@Transactional
	public List<Stock> signStockforMarket(List<Stock> stocks, int idMarket) {
		Market market = marketRepository.findOne(idMarket);
		List<Stock> stockDisconnect = stockRepository.findByMarkets(market);
		for (int index = 0; index < stockDisconnect.size(); index++) {
			stockDisconnect.get(index).markets.clear();
		}
		for (int index = 0; index < stocks.size(); index++) {
			Stock stockConnect=stockRepository.findOne(stocks.get(index).getId());
			stockConnect.markets.add(market);
		}
		return stocks;
	}
}
