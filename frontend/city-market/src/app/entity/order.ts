import {Market} from './merket';
import {Product} from './product';

export class Order {
  public id: number;
  public nameUser: string;
  public contacts: string;
  public address: string;
  public status: string;
  public market: Market;
  public product: Product[];
}
