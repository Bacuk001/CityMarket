import {Product} from "./product";

/**
 * Class containing information about the product descriptionю
 */
export class Description {
  /**
   * Identification number of the description.
   */
  public id: number;
  /**
   * Name of the description.
   */
  public name: string;
  /**
   * The meaning of the description.
   */
  public value: string;
  /**
   * The product to which the description belongs.
   */
  public product: Product;
}
