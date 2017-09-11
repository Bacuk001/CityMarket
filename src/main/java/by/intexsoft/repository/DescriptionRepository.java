package by.intexsoft.repository;

import java.util.List;

import by.intexsoft.entity.Description;
import by.intexsoft.entity.Product;

/**
 * In the object of this class, the description and the value of the
 * characteristic of the goods, which the goods possess, are stored.
 */
public interface DescriptionRepository extends AbstractEntityRepository<Description> {
	/**
	 * A method that extracts from the database a list of characteristics
	 * corresponding to the name.
	 * 
	 * @param nameDescription
	 *            name of description.
	 * @return {@link List}<{@link Description}>
	 */
	List<Description> findByName(String nameDescription);

	/**
	 * The method looks for all the characteristics corresponding to the value
	 * passed to it.
	 * 
	 * @param velue
	 *            the characteristic value.
	 * @return {@link List} <{@link Description}>
	 */
	List<Description> findByValue(String velue);

	/**
	 * The method returns a description of the product.
	 * 
	 * @param product
	 *            the product whose characteristics you want to find in the
	 *            database.
	 */
	List<Description> findByProduct(Product product);
}
