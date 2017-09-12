package by.intexsoft.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productRepository;

	@Autowired
	ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> findByName(String nameProduct) {
		return productRepository.findByName(nameProduct);
	}

	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> finByStock(Stock stock) {
		return productRepository.findByStocks(stock);
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Product findOne(int id) {
		return productRepository.findOne(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	/**
	 * Deletes the product by id.
	 */
	public void delete(int id) {
		productRepository.delete(id);
	}

	public List<Product> findByMarket(Market market) {
		List<Product> products = productRepository.findByMarkets(market);
		return products;
	}

	public List<Product> findByMarketAndCategory(Market market, Category category) {
		return productRepository.findByMarketsAndCategory(market, category);
	}

	public List<Product> findByStockAndCategory(Stock stock, Category category) {
		return productRepository.findByStocksAndCategory(stock, category);
	}

	public Integer countByMarketsAndCategory(Market market, Category category) {
		return productRepository.countByMarketsAndCategory(market, category);
	}

	public Integer countByStockAndCategory(Stock stock, Category category) {
		return productRepository.countByStocksAndCategory(stock, category);
	}
}
