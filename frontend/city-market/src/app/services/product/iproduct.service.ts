import {Product} from '../../entities/product';

/**
 * The interface defining methods of interaction with products in the application.
 */
export interface IProductService {
  /**
   * The method determines whether to load products by the warehouse or the store, forms a request
   * and sends it to receive the products. Returns Promise.
   * @param {number} page
   */
  getPromiseProducts(page: number);

  /**
   * The method returns a list of products stored in the service.
   */
  getProducts();

  /**
   * The method generates a request to save the product and sends it for storage.
   * @param {Product} product
   */
  saveProduct(product: Product);

  /**
   * The method returns the product that was selected for the operations.
   */
  getSelectProduct();

  /**
   * Establishes the product over which the actions will be performed.
   * @param {Product} product
   */
  setSelectProduct(product: Product);

  /**
   * The method generates and sends a request to obtain the number of products.
   */
  loadCountProduct();

  /**
   * The method sends a request for all products.
   */
  getAllProducts();

}
