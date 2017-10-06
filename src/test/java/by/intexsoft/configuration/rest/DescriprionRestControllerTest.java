package by.intexsoft.configuration.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import by.intexsoft.entity.Description;
import by.intexsoft.entity.Product;
import by.intexsoft.rest.DescriprionRestController;
import by.intexsoft.service.DescriptionService;
import by.intexsoft.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class DescriprionRestControllerTest {
	private static final String MESSAGE = "Message";
	private static final String DESCRIPTION_NOT_FOUND = "Description for product not found.";
	@Mock
	private DescriptionService descriptionService;
	@Mock
	private ProductService productService;
	@InjectMocks
	private DescriprionRestController descriprionRestController = new DescriprionRestController(descriptionService,
			productService);

	@Test
	public void getByProductTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		Product product = new Product();
		when(productService.findOne(5)).thenReturn(product);
		when(descriptionService.findByProduct(product)).thenReturn(new ArrayList<Description>());
		ResponseEntity<List<Description>> response = new ResponseEntity<List<Description>>(new ArrayList<Description>(),
				headers, HttpStatus.OK);
		assertEquals(descriprionRestController.getByProduct(5), response);
	}

	@Test
	public void save() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		List<Description> descriptions = new ArrayList<>();
		when(descriptionService.saveListDescription(descriptions)).thenReturn(descriptions);
		ResponseEntity<List<Description>> response = new ResponseEntity<List<Description>>(headers, HttpStatus.OK);
		assertEquals(descriprionRestController.save(descriptions), response);
		when(descriptionService.saveListDescription(descriptions)).thenReturn(null);
		headers.add(MESSAGE, "Descriptions do not save");
		response = new ResponseEntity<List<Description>>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(descriprionRestController.save(descriptions), response);
	}
}
