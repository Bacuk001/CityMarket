package by.intexsoft.configuration.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.CategoryRepository;
import by.intexsoft.repository.ProductRepository;
import by.intexsoft.repository.StockRepository;

/**
 * A method that works with the product repository. the method extracts, adds
 * and removes products.
 *
 * @see {@link JpaRepository}, {@link ProductRepository}, {@link Product}
 */
@Service
public class ProductService {
	private ProductRepository productRepository;
	private StockRepository stockRepository;
	private CategoryRepository categoryRepository;

	ProductService(CategoryRepository categoryRepository, StockRepository stockRepository,
			ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.stockRepository = stockRepository;
		this.categoryRepository = categoryRepository;
	}

	/**
	 * A service method that requests information from a database about a product
	 * whose name matches the name passed in the parameters.
	 */
	public Product findByName(String nameProduct) {
		return productRepository.findByName(nameProduct);
	}

	/**
	 * The service method that causes information from the repository about all
	 * products that are in the category. the method takes an object of a category.
	 * 
	 * @return {@link List}<{@link Product}>
	 */
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	/**
	 * A service method that requests information from the repository about the
	 * goods with which the warehouse operates.
	 */
	public List<Product> finByStock(Stock stock) {
		return productRepository.findByStocks(stock);
	}

	/**
	 * A service method that stores the product into a database.
	 */
	@Transactional
	public Product save(Product product, int idCategory, int idStock) {
		Stock stock = stockRepository.findOne(idStock);
		Category category = categoryRepository.findOne(idCategory);
		product.category = category;
		product = productRepository.save(product);
		stock.product.add(product);
		return product;
	}

	/**
	 * The service method accepts the product ID, accesses the repository to search
	 * for products by id. if such a product is returned by the object.
	 */
	public Product findOne(int id) {
		return productRepository.findOne(id);
	}

	/**
	 * The service method invokes all products from the repository
	 */
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	/**
	 * Deletes the product by id.
	 */
	public void delete(int id) {
		productRepository.delete(id);
	}

	/**
	 * The service method takes the market and returns all the products with which
	 * this store operates.
	 * 
	 * @see {@link Market}
	 */
	public List<Product> findByMarket(Market market) {
		List<Product> products = productRepository.findByMarkets(market);
		return products;
	}

	/**
	 * The service method takes the market and category and returns all the products
	 * that this store operates with and corresponds to the transferred category.
	 * 
	 * @see {@link Market} , {@link Category}
	 */
	public List<Product> findByMarketAndCategory(Market market, Category category) {

		return productRepository.findByMarketsAndCategory(market, category);
	}

	/**
	 * The service method accepts the warehouse and category and returns all
	 * products with which this warehouse operates and corresponds to the
	 * transferred category.
	 * 
	 * @see {@link Category}, {@link Stock}
	 */
	public List<Product> findByStockAndCategory(Stock stock, Category category) {
		return productRepository.findProductDistinctByStocksAndCategory(stock, category);
	}

	/**
	 * The service method accepts the warehouse and category and returns the number
	 * of records the products with which this market and corresponds to the
	 * transferred category.
	 * 
	 * @see {@link Category}, {@link Stock}
	 */
	public Integer countByMarketsAndCategory(Market market, Category category) {
		return productRepository.countByMarketsAndCategory(market, category);
	}

	/**
	 * The service method accepts the warehouse and category and returns the number
	 * of records the products with which this stock and corresponds to the
	 * transferred category.
	 * 
	 * @see {@link Category}, {@link Stock}
	 */
	public Integer countByStockAndCategory(Stock stock, Category category) {
		return productRepository.countProductDistinctByStocksAndCategory(stock, category);
	}

	/**
	 * The service method accesses the repository to retrieve data about the product
	 * that is contained in the repositories in a specific group.
	 * 
	 * @see {@link Category}, {@link Stock}, {@link ProductRepository}
	 */
	public List<Product> findByCategoryAndStocks(Category category, List<Stock> stocks) {
		return productRepository.findProductDistinctByCategoryAndStocksIn(category, stocks);
	}

	/**
	 * The service method asks the repository for all products in the category in
	 * the warehouse, the number of records configured in the {@link Pageable}.
	 * 
	 * @see {@link ProductRepository}, {@link Pageable}, {@link Stock},
	 *      {@link Product}.
	 */
	public List<Product> findByCategoryAndStocksPage(Category category, List<Stock> stocks, Pageable pageable) {
		return productRepository.findProductDistinctByCategoryAndStocksIn(category, stocks, pageable);
	}

	/**
	 * The service method asks the repository for the number of products in the
	 * category in the stock.
	 * 
	 * @see {@link ProductRepository}, {@link Category}, {@link Stock}
	 */
	public Integer countProductByCategoryAndStocks(Category category, List<Stock> stocks) {
		return productRepository.countProductDistinctByCategoryAndStocksIn(category, stocks);
	}
}
