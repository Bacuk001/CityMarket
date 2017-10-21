package by.intexsoft.configuration.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import by.intexsoft.entity.Price;
import by.intexsoft.entity.Product;
import by.intexsoft.entity.Stock;
import by.intexsoft.repository.CategoryRepository;
import by.intexsoft.repository.MarketRepository;
import by.intexsoft.repository.PriceRepository;
import by.intexsoft.repository.ProductRepository;
import by.intexsoft.repository.StockRepository;
import by.intexsoft.service.impl.PriceServicse;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

	@Mock
	private PriceRepository priceRepository;
	@Mock
	private MarketRepository marketRepository;
	@Mock
	private ProductRepository productRepository;
	@Mock
	private StockRepository stockRepository;
	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	PriceServicse priceServicse = new PriceServicse(priceRepository, marketRepository, productRepository,
			stockRepository, categoryRepository);

	@Test
	public void testUserService() {
		when(priceServicse.findByProductAndStock(new Product(), new Stock())).thenReturn(new ArrayList<Price>());
		assertEquals(priceServicse.findByProductAndStock(new Product(), new Stock()), new ArrayList<Price>());
		when(priceServicse.findByProductAndStocks(new Product(), new ArrayList<Stock>()))
				.thenReturn(new ArrayList<Price>());
		assertEquals(priceServicse.findByProductAndStocks(new Product(), new ArrayList<Stock>()),
				new ArrayList<Price>());
		Price price = new Price();
		when(priceServicse.save(price)).thenReturn(price);
		assertEquals(priceServicse.save(price), price);
	}
}
