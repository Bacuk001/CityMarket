import {Market} from './merket';
import {Stock} from './stock';
import {Role} from './role';

export class User {
  public id: number;
  public name: string;
  public password: string;
  public market: Market;
  public stock: Stock;
  public roles: Role[];
}
