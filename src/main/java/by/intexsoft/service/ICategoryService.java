package by.intexsoft.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.entity.Category;
import by.intexsoft.repository.CategoryRepository;

/**
 * Service providing a level of data service extracting from the repository
 * which in turn operates with data in the database.
 * 
 * @see {@link Category}, {@link JpaRepository}, {@link CategoryRepository}
 */
public interface ICategoryService {
	/**
	 * The method accesses the repository and requests information about the
	 * category. The repository, by operating an object in the database, checks
	 * whether there is such a category in the database. If there is no object, it
	 * returns null if there is that object.
	 * 
	 * @return {@link Category}
	 */
	Category findByName(String nameCategory);

	/**
	 * The method accesses the repository and requests all categories of goods in
	 * the database.
	 * 
	 * @return {@link List}<{@link Category}>
	 */
	List<Category> findAll();

	/**
	 * A method that requests from the repository data about a category that has the
	 * value id whose value is passed in the parameters.
	 * 
	 * @return {@link Category}
	 */
	Category findOne(int id);

	/**
	 * The method passes the id of the category that you want to delete to the
	 * repository.
	 */
	void delete(int id);

	/**
	 * The method passes the created category object to the repository for saving to
	 * the database.
	 * 
	 * @see {@link Category}
	 */
	Category save(Category category);
}
