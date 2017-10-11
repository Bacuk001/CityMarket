package by.intexsoft.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import by.intexsoft.entity.Category;
import by.intexsoft.entity.Description;
import by.intexsoft.entity.Product;
import by.intexsoft.repository.DescriptionRepository;

/**
 * The service interacts with the repository and manipulates the data. the
 * service sends data to the repository for saving, requests information to be
 * extracted from the database. The service works with a repository that
 * operates on data in the database.
 *
 * @see {@link JpaRepository}, {@link DescriptionRepository},
 *      {@link Description}
 */
public interface IDescriptionService {
	/**
	 * The method takes the category name and poisons the JpaRepository which will
	 * send the query to the database to retrieve the category with that name. when
	 * this method gets naked it will return them to the user.
	 * 
	 * @return {@link List}<{@link Description}>
	 */
	public List<Description> findByName(String nameDescription);

	/**
	 * The method passes the value of the parameter to the repository, to search for
	 * all characteristics whose characteristic value corresponds to the transmitted
	 * parameters. the repository forms the request forms a query into the database,
	 * receives the result, and passes it to the method.
	 * 
	 * @return {@link List}<{@link Description}>
	 */
	public List<Description> findByValue(String nameValue);

	/**
	 * The method is passed to the product to determine which category it belongs
	 * to. The method takes the product and sends it to the repository, which forms
	 * the query into the database when the data comes from the database to the
	 * repository, it is passed to the method, and the method returns data to the
	 * user of our class.
	 * 
	 * @return {@link List} <{@link Description}>
	 */
	public List<Description> findByProduct(Product product);

	/**
	 * The service method takes a category object and sends it to the repository to
	 * be saved to the database, if the object is saved then the method returns an
	 * object if there is no null.
	 */
	public Description seve(Description description);

	/**
	 * The method queries data about all stored categories in the database from the
	 * repository. After receiving an array of objects, the service method returns
	 * to the user of the service.
	 */
	public List<Description> findAll();

	/**
	 * The method accepts category IDs and requests information from the repository
	 * to retrieve a category from the database whose id matches with the passed in
	 * parameters.
	 * 
	 * @return {@link Category}
	 */
	public Description findOne(int id);

	/**
	 * The method takes an array of categories and renders one object per repository
	 * to save to the database.
	 */
	@Transactional
	public List<Description> saveListDescription(List<Description> descriptions);

	/**
	 * Deletes the description by id.
	 */
	public void delete(int id);
}
