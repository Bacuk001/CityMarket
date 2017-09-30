import {Market} from './merket';
import {Stock} from './stock';
import {Role} from './role';

/**
 * User registered in the system.
 */
export class User {
  /**
   * An identification number.
   */
  public id: number;
  /**
   * Username.
   */
  public name: string;
  /**
   * User password.
   */
  public password: string;
  /**
   * A market in which the user is appointed as a manager.
   */
  public market: Market;
  /**
   * Warehouse in which the user is appointed as a manager.
   */
  public stock: Stock;
  /**
   * List of roles that the user has.
   */
  public roles: Role[];
}
