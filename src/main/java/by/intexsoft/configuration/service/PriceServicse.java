package by.intexsoft.configuration.service;

import java.util.List;

import org.springframework.stereotype.Service;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Price;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.PriceRepository;

/**
 * Service that processes information about prices. Contacting the repository
 * receives data, processes it, and sends it to the user.
 * 
 * @see {@link PriceRepository}, {@link Price}
 */
@Service
public class PriceServicse {
	private PriceRepository priceRepository;

	PriceServicse(PriceRepository priceRepository) {
		this.priceRepository = priceRepository;
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

	/**
	 * The service method takes an instance of the product class and an instance of
	 * the warehouse class, and accesses the store to retrieve the prices.
	 * 
	 * @see {@link Product} , {@link PriceRepository}, {@link Stock}
	 */
	public List<Price> findByProductAndStock(Product product, Stock stock) {
		return priceRepository.findByProductAndStock(product, stock);
	}

	/**
	 * The service method processes requests to save the price.
	 * 
	 */
	public Price save(Price price) {
		return priceRepository.save(price);
	}
}
