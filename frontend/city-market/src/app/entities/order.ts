import {Market} from './merket';
import {Product} from './product';

/**
 * Orders processed on the market.
 */
export class Order {
  /**
   * An identification number.
   */
  public id: number;
  /**
   * Username.
   */
  public nameUser: string;
  /**
   * Information on which you can find out how to contact the buyer.
   */
  public contacts: string;
  /**
   * Contains information about the address of the residence of the buyer.
   */
  public address: string;
  /**
   * Order status. Waiting, denied, approved.
   */
  public status: string;
  /**
   * The market in which the order is placed.
   */
  public market: Market;
  /**
   * Products in the order.
   */
  public product: Product[];
}
