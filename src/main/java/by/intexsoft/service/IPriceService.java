package by.intexsoft.service;

import java.util.List;

import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Price;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.CategoryRepository;
import by.intexsoft.repository.MarketRepository;
import by.intexsoft.repository.PriceRepository;
import by.intexsoft.repository.StockRepository;

/**
 * Service that processes information about prices. Contacting the repository
 * receives data, processes it, and sends it to the user.
 * 
 * @see {@link PriceRepository}, {@link Price}
 */
public interface IPriceService {
	/**
	 * The service method returns the products available in the store based on the
	 * warehouses to which the store is subscribed.
	 * 
	 * @see {@link Product}, {@link Market}, {@link Stock}, {@link PriceRepository}
	 */
	public List<Price> findByProductAndStocks(Product product, List<Stock> stocks);

	/**
	 * The service method takes an instance of the product class and an instance of
	 * the warehouse class, and accesses the store to retrieve the prices.
	 * 
	 * @see {@link Product} , {@link PriceRepository}, {@link Stock}
	 */
	public List<Price> findByProductAndStock(Product product, Stock stock);

	/**
	 * The service method processes requests to save the price.
	 */
	public Price save(Price price);

	/**
	 * The service method takes store id and category id to get prices for products
	 * that are available from the store. The method accesses the repository to
	 * retrieve data. Having received the response.
	 * 
	 * @see {@link MarketRepository}, {@link CategoryRepository},
	 *      {@link StockRepository}
	 */
	public List<Price> findPriceByMarketAndCategory(int idCategory, int idMarket, String direction, int pageSize,
			int namberPage);

	/**
	 * The service method accesses the repository to obtain the number of price
	 * records for the list of products in the warehouses that are transferred in
	 * the parameters. Products that are not in stock are excluded.
	 * 
	 * @see {@link PriceRepository}, {@link Market}, {@link Category},
	 *      {@link Product}
	 */
	public int countPricesOfProductsMyMarketAndCategory(int idCategory, int idMarket);

}
