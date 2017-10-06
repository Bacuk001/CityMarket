package by.intexsoft.configuration.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import by.intexsoft.entity.Category;
import by.intexsoft.entity.Market;
import by.intexsoft.repository.CategoryRepository;
import by.intexsoft.repository.MarketRepository;
import by.intexsoft.repository.PriceRepository;
import by.intexsoft.repository.ProductRepository;
import by.intexsoft.repository.StockRepository;
import by.intexsoft.rest.ProductMarketContoller;
import by.intexsoft.rest.ProductRestController;
import by.intexsoft.service.CategoryService;
import by.intexsoft.service.MarketService;
import by.intexsoft.service.PriceServicse;
import by.intexsoft.service.ProductService;
import by.intexsoft.service.StockService;

@RunWith(MockitoJUnitRunner.class)
public class ProductMarketContollerTest {
	private static final String CONT_PRODUCT = "Cont product by stock and category.";
	private static final String FIND_PRODUCT_BY_STOCK_AND_CATEGORY = "Find product by stock and category on page.";
	private static final String FIND_PRODYCTS_BY_MARKET = "Find prodycts by market.";
	private static final String APPLICATION_JSON = "application/json; charset=UTF-8";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String FIND_PRODUCT_BY_MARKET_AND_CATEGORY = "Find product by market and category.";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);
	private static final String PRODUCTS_NOT_FOUND = "Products not found.";
	private static final String MESSAGE = "Message";
	@Mock
	private CategoryRepository categoryRepository;
	@Mock
	private MarketRepository marketRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private PriceRepository priceRepository;
	@Mock
	private StockRepository stockRepository;
	@InjectMocks
	private CategoryService categoryService = new CategoryService(categoryRepository);
	@InjectMocks
	private MarketService marketService = new MarketService(marketRepository);
	@InjectMocks
	private StockService stockService = new StockService(marketRepository, stockRepository);
	@InjectMocks
	private PriceServicse priceServicse = new PriceServicse(priceRepository);
	@InjectMocks
	private ProductService productService = new ProductService(categoryRepository, stockRepository, productRepository);
	@InjectMocks
	private ProductMarketContoller productMarketContoller = new ProductMarketContoller(marketService, categoryService,
			productService, stockService, priceServicse);

	@Test
	public void conuntByMarketAndCategoryTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		Market market = new Market();
		Category category = new Category();
		Integer count = new Integer(0);
		when(marketRepository.findOne(1)).thenReturn(market);
		when(categoryRepository.findOne(1)).thenReturn(category);
		when(productRepository.countByMarketsAndCategory(market, category)).thenReturn(count);
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(count, headers, HttpStatus.OK);
		assertEquals(productMarketContoller.conuntByMarketAndCategory(1, 1), response);
	}

}
