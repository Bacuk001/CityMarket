import {Category} from '../../entities/category';

/**
 * The interface that defines the basic actions for working with product categories.
 */
export interface ICategoryService {
  /**
   * The method generates a request for all categories of goods. Returns Promise.
   */
  getPromiseCategories();

  /**
   * The method returns a list of categories stored in the service.
   */
  getCategories();

  /**
   * The method returns the selected category.
   */
  getSelectedCategory();

  /**
   * The method sets the category
   */
  setCategory(category: Category);

  /**
   * The method generates a query to store the category in the database.
   */
  saveCategory(category: Category);

  /**
   * The method generates a query to remove the category in the database.
   */
  deleteCategory(category: Category);
}

