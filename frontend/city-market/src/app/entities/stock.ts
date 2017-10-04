import {Product} from './product';

/**
 * The essence of the warehouse in which products are stored.
 */
export class Stock {
  /**
   * Warehouse identification number;
   */
  public id: number;
  /**
   * Name of the warehouse.
   */
  public name: string;
  /**
   * Warehouse location address.
   */
  public address: string;
  /**
   * Warehouse description.
   */
  public about: string;
  /**
   * Products that store the warehouse.
   */
  public products: Product[];
}
