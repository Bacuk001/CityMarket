package by.intexsoft.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import by.intexsoft.entity.Category;
import by.intexsoft.repository.CategoryRepository;

/**
 * Service providing a level of data service extracting from the repository
 * which in turn operates with data in the database.
 * 
 * @see {@link Category}, {@link JpaRepository}, {@link CategoryRepository}
 */
@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	@Autowired
	CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	/**
	 * The method accesses the repository and requests information about the
	 * category. The repository, by operating an object in the database, checks
	 * whether there is such a category in the database. If there is no object, it
	 * returns null if there is that object.
	 * 
	 * @return {@link Category}
	 */
	public Category findByName(String nameCategory) {
		return categoryRepository.findByName(nameCategory);
	}

	/**
	 * The method accesses the repository and requests all categories of goods in
	 * the database.
	 * 
	 * @return {@link List}<{@link Category}>
	 */
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	/**
	 * A method that requests from the repository data about a category that has the
	 * value id whose value is passed in the parameters.
	 * 
	 * @return {@link Category}
	 */
	public Category findOne(int id) {
		return categoryRepository.findOne(id);
	}

	/**
	 * The method passes the id of the category that you want to delete to the
	 * repository.
	 */
	public void delete(int id) {
		categoryRepository.delete(id);
	}

	/**
	 * The method passes the created category object to the repository for saving to
	 * the database.
	 * 
	 * @see {@link Category}
	 */
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
}
