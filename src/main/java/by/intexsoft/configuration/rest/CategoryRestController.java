package by.intexsoft.configuration.rest;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import by.intexsoft.configuration.service.CategoryService;
import by.intexsoft.entity.Category;

@RestController
public class CategoryRestController {
	private static final String MESSAGE = "Message";
	private static final String ERROR_OF_CREATING_A_CATEGORY = "Error of creating a category, an entry with this name exists in the database.";
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestController.class);
	CategoryService categoryService;

	@Autowired
	CategoryRestController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * @param nameCategory
	 * @return
	 */
	@RequestMapping(value = "/category/{name}", method = RequestMethod.GET)
	public ResponseEntity<Category> getByName(@PathVariable("name") String nameCategory) {
		LOGGER.info("Start find category by name.");
		Category category = categoryService.findByName(nameCategory);
		if (category == null)
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}

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

	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> delete(@PathVariable("id") int id) {
		categoryService.delete(id);
		return new ResponseEntity<Category>(HttpStatus.OK);
	}
}
