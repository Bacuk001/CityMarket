import {Product} from '../../entities/product';
import {Price} from '../../entities/price';

/**
 *  Interface describing the basic methods for working with the prices of products.
 */
export interface IPriceService {
  /**
   * The method determines for which the rest should be loaded. The remains of a warehouse or
   *  shop. Generates a request for data and requests a return promise.
   */
  getPriceByProduct(product: Product)

  /**
   *The method generates a query to store the price of the product in the database.
   * @param {Price} price
   */
  savePrice(price: Price);
}
