package by.intexsoft.configuration.rest;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;

import by.intexsoft.entity.Category;
import by.intexsoft.rest.CategoryRestController;
import by.intexsoft.service.CategoryService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryRestControllerTest {
	private static final String MESSAGE = "Message";
	private static final String ERROR_OF_CREATING_A_CATEGORY = "Error of creating a category, an entry with this name exists in the database.";
	@Mock
	CategoryService categoryService;
	@InjectMocks
	CategoryRestController categoryRestController = new CategoryRestController(categoryService);

	@Test
	public void getByNameTest() {
		Category category = new Category();
		when(categoryService.findByName("")).thenReturn(category);
		ResponseEntity<Category> response = new ResponseEntity<Category>(category, HttpStatus.OK);
		assertEquals(categoryRestController.getByName(""), response);
		when(categoryService.findByName("sam")).thenReturn(null);
		response = new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		assertEquals(categoryRestController.getByName("sam"), response);
	}

	@Test
	public void createCategoryText() {
		Category category = new Category();
		category.name = "sam";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		ResponseEntity<Category> response = new ResponseEntity<Category>(category, headers, HttpStatus.OK);
		when(categoryService.save(category)).thenReturn(category);
		when(categoryService.findByName("sam")).thenReturn(null);
		assertEquals(categoryRestController.createCaterory(category), response);
		category.name = "sam2";
		when(categoryService.findByName("sam2")).thenReturn(category);
		headers.add(MESSAGE, ERROR_OF_CREATING_A_CATEGORY);
		response = new ResponseEntity<Category>(null, headers, HttpStatus.FORBIDDEN);
		assertEquals(categoryRestController.createCaterory(category), response);
	}

	@Test
	public void getAllCategoryTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		when(categoryService.findAll()).thenReturn(new ArrayList<Category>());
		ResponseEntity<List<Category>> response = new ResponseEntity<List<Category>>(new ArrayList<Category>(), headers,
				HttpStatus.OK);
		assertEquals(categoryRestController.getAllCategory(), response);
		when(categoryService.findAll()).thenReturn(null);
		headers.add(MESSAGE, "Error find all category from database");
		response = new ResponseEntity<List<Category>>(headers, HttpStatus.NOT_FOUND);
		assertEquals(categoryRestController.getAllCategory(), response);
	}
}