package by.intexsoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
import by.intexsoft.entity.Price;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.CategoryRepository;
import by.intexsoft.repository.MarketRepository;
import by.intexsoft.repository.PriceRepository;
import by.intexsoft.repository.ProductRepository;
import by.intexsoft.repository.StockRepository;
import by.intexsoft.service.IPriceService;

/**
 * Service that processes information about prices. Contacting the repository
 * receives data, processes it, and sends it to the user.
 * 
 * @see {@link PriceRepository}, {@link Price}
 */
@Service
public class PriceServicse implements IPriceService {
	private static final String NAME_FIELD_SORTING = "price";
	private PriceRepository priceRepository;
	private MarketRepository marketRepository;
	private ProductRepository productRepository;
	private StockRepository stockRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public PriceServicse(PriceRepository priceRepository, MarketRepository marketRepository,
			ProductRepository productRepository, StockRepository stockRepository,
			CategoryRepository categoryRepository) {
		this.priceRepository = priceRepository;
		this.marketRepository = marketRepository;
		this.productRepository = productRepository;
		this.stockRepository = stockRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Price> findByProductAndStocks(Product product, List<Stock> stocks) {
		return priceRepository.findPriceDistinctByProductAndStockIn(product, stocks);
	}

	@Override
	public List<Price> findByProductAndStock(Product product, Stock stock) {
		return priceRepository.findByProductAndStock(product, stock);
	}

	@Override
	public Price save(Price price) {
		return priceRepository.save(price);
	}

	@Override
	public List<Price> findPriceByMarketAndCategory(int idCategory, int idMarket, String direction, int pageSize,
			int namberPage) {
		Market market = marketRepository.findOne(idMarket);
		Category category = categoryRepository.findOne(idCategory);
		List<Stock> stocks = stockRepository.findByMarkets(market);
		List<Product> products = productRepository.findProductDistinctByCategoryAndStocksIn(category, stocks);
		List<Price> prices = priceRepository.findPriceDistinctByProductInAndStockInAndInStockNotLike(products, stocks,
				0, new PageRequest(namberPage, pageSize, Direction.fromString(direction), NAME_FIELD_SORTING));
		return prices;
	}

	@Override
	public int countPricesOfProductsMyMarketAndCategory(int idCategory, int idMarket) {
		Market market = marketRepository.findOne(idMarket);
		Category category = categoryRepository.findOne(idCategory);
		List<Stock> stocks = stockRepository.findByMarkets(market);
		List<Product> products = productRepository.findProductDistinctByCategoryAndStocksIn(category, stocks);
		return priceRepository.countPriceDistinctByProductInAndStockInAndInStockNotLike(products, stocks, 0);
	}
}
