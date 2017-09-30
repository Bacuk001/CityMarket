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
import by.intexsoft.repository.PriceRepository;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

	@Mock
	PriceRepository priceRepository;

	@InjectMocks
	PriceServicse priceServicse = new PriceServicse(priceRepository);

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
