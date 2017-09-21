package by.intexsoft.configuration.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Price;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.PriceRepository;
import by.intexsoft.repository.ProductRepository;
import by.intexsoft.repository.StockRepository;

/**
 * Service that processes information about prices. Contacting the repository
 * receives data, processes it, and sends it to the user.
 * 
 * @see {@link PriceRepository}, {@link Price}
 */
@Service
public class PriceServicse {

	private PriceRepository priceRepository;
	private StockRepository stockRepository;
	private ProductRepository productRepository;

	@Autowired
	PriceServicse(StockRepository stockRepository, PriceRepository priceRepository,
			ProductRepository productRepository) {
		this.priceRepository = priceRepository;
		this.stockRepository = stockRepository;
		this.productRepository = productRepository;
	}

	/**
	 * The service method returns the products available in the store based on the
	 * warehouses to which the store is subscribed.
	 * 
	 * @see {@link Product}, {@link Market}, {@link Stock}, {@link PriceRepository}
	 */
	public List<Price> findByProductAndStocks(Product product, List<Stock> stocks) {
		return priceRepository.findPriceDistinctByProductAndStockIn(product, stocks);
	}
    
    public Price findByProductAndStock(Product product, Stock stock) {
		return priceRepository.findByProductAndStock(product, stock);
	}

	/**
	 * The service method processes requests to save the price.
	 * 
	 */
	@Transactional
	public Price save(Price price) {
		return priceRepository.save(price);
	}
}
