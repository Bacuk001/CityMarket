package by.intexsoft.configuration.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.intexsoft.configuration.service.DescriptionService;
import by.intexsoft.configuration.service.ProductService;
import by.intexsoft.entity.Description;
import by.intexsoft.entity.Product;

/**
 * A controller that processes requests for a description, the controller works
 * with a service that provides information. {@link RestController},
 * 
 * @see {@link DescriptionService}, {@link Description} {@link ResponseEntity}.
 */
@RestController
public class DescriprionRestController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DescriprionRestController.class);
	private static final String MESSAGE = "Message";
	private DescriptionService descriptionService;
	private ProductService productService;

	DescriprionRestController(DescriptionService descriptionService, ProductService productService) {
		this.descriptionService = descriptionService;
		this.productService = productService;
	}

	/**
	 * A controller that processes requests for a description of the product.
	 * 
	 * @see {@link Product}
	 */
	@RequestMapping(value = "/description/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Description>> getByProduct(@PathVariable("id") int id) {
		LOGGER.info("Find Descriprion by product in database.");
		Product product = productService.findOne(id);
		List<Description> descriptions = descriptionService.findByProduct(product);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (descriptions == null) {
			LOGGER.info("Description for product not found.");
			headers.add(MESSAGE, "Product description not fond");
			return new ResponseEntity<List<Description>>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Description>>(descriptions, headers, HttpStatus.OK);
	}

	/**
	 * A controller that processes requests to save a list of descriptions. the
	 * controller receives a list and sends it to the service for saving. After
	 * successful execution, the controller sends a response that the save was
	 * successful.
	 */
	@RequestMapping(value = "/description/save", method = RequestMethod.POST)
	public ResponseEntity<List<Description>> save(@RequestBody List<Description> descriptions) {
		LOGGER.info("Save list descriptions for product.");
		HttpHeaders headers = new HttpHeaders();
		System.out.println("********"+descriptions.get(0).product.getId()+"========================");
		headers.add("Content-Type", "application/json; charset=UTF-8");
		descriptions = descriptionService.seveListDescription(descriptions);
		if (descriptions == null) {
			headers.add(MESSAGE, "Descriptions do not save");
			return new ResponseEntity<List<Description>>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Description>>(headers, HttpStatus.OK);
	}
}
