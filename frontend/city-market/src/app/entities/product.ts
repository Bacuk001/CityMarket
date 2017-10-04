import {Stock} from './stock';
import {Category} from './category';
import {Price} from './price';

/**
 * Product registered in the application.
 */
export class Product {
  /**
   * Product identification number.
   */
  public id: number;
  /**
   * The product's name.
   */
  public name: string;
  /**
   * Warehouse on which the product is stored
   */
  public stock: Stock;
  /**
   * The category to which the product refers.
   */
  public category: Category;
  /**
   * Product prices.
   */
  public price: Price[];
  /**
   * Product prices.
   */
  public priceView: string;
  /**
   * Photograph of product.
   */
  public urlPhoto: string;
}
