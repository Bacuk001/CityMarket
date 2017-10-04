import {Stock} from './stock';
import {Product} from './product';

/**
 * Class stores information about the price, balances and reserve for the product.
 */
export class Price {

  constructor(public price: number, public inStock: number, public inReserv: number) {
  }

  /**
   * Product identification number.
   */
  public id: number;
  /**
   * Warehouse on which the product is stored.
   */
  public stock: Stock;
  /**
   * The product to which the price class belongs.
   */
  public product: Product;
}
