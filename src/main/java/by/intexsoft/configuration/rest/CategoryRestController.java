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
import by.intexsoft.configuration.service.CategoryService;
import by.intexsoft.configuration.service.MarketService;
import by.intexsoft.configuration.service.StockService;
import by.intexsoft.entity.Category;

/**
 * A controller that processes requests for product categories. The controller
 * accepts requests, processes information, and returns responses to the user.
 * the controller can receive the object and send it to the
 * {@link CategoryService} for storage, can process transfer control of the
 * {@link CategoryService} to remove the category.
 * 
 * @see {@link RestController}, {@link CategoryService}, {@link Category}
 */
@RestController
public class CategoryRestController {
	private static final String MESSAGE = "Message";
	private static final String ERROR_OF_CREATING_A_CATEGORY = "Error of creating a category, an entry with this name exists in the database.";
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestController.class);
	private CategoryService categoryService;

	CategoryRestController(StockService stockService, MarketService marketService, CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * A controller that processes requests for a category by name. Requests the
	 * {@linkplain CategoryService} for a category with a name. The received answer
	 * is put in {@link ResponseEntity} and sent to the user.
	 */
	@RequestMapping(value = "/category/{name}", method = RequestMethod.GET)
	public ResponseEntity<Category> getByName(@PathVariable("name") String nameCategory) {
		LOGGER.info("Start find category by name.");
		Category category = categoryService.findByName(nameCategory);
		if (category == null)
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

	/**
	 * A controller that processes requests for adding a category. the controller
	 * receives a category object and sends it to the {@linkplain CategoryService}
	 * for storage.
	 */
	@RequestMapping(value = "/category/save", method = RequestMethod.POST)
	public ResponseEntity<Category> createCaterory(@RequestBody Category category) {
		LOGGER.info("Create new category");
		Category categoryFind = categoryService.findByName(category.name);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (categoryFind != null) {
			LOGGER.info("An attempt to record existing categories.");
			headers.add(MESSAGE, ERROR_OF_CREATING_A_CATEGORY);
			return new ResponseEntity<Category>(null, headers, HttpStatus.FORBIDDEN);
		}
		category = categoryService.save(category);
		return new ResponseEntity<Category>(category, headers, HttpStatus.CREATED);
	}

	/**
	 * The controller that processes requests receives all the saved categories. The
	 * controller accesses the {@linkplain CategoryService} and receives a list of
	 * categories. Prepares the {@link ResponseEntity} and sends it to the user.
	 */
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getAllCategory() {
		LOGGER.info("Get all categories");
		List<Category> categories = categoryService.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		if (categories == null) {
			headers.add(MESSAGE, "Error find all category from database");
			return new ResponseEntity<List<Category>>(headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Category>>(categories, headers, HttpStatus.OK);
	}

	/**
	 * A controller that processes requests for a category by id. the controller
	 * accesses the {@linkplain CategoryService} and receives the category object,
	 * places it in {@link ResponseEntity} and sends it to the user.
	 */
	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> delete(@PathVariable("id") int id) {
		categoryService.delete(id);
		return new ResponseEntity<Category>(HttpStatus.OK);
	}
}
