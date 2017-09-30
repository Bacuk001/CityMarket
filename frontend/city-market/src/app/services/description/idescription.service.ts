import {Product} from "../../entities/product";
import {Description} from "../../entities/description";

/**
 * The interface describes the basic methods with the work of describing products.
 */
export interface IDescriptionService {
  /**
   * The method generates a query to obtain descriptions for the goods.
   */
  getDescriptionsByProduct(product: Product);

  /**
   * The method generates a request to store the goods in the database.
   */
  saveDescription(description: Description[]);
}
