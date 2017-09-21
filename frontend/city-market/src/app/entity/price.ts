import {Stock} from './stock';
import {Product} from './product';

export class Price {
  public id: number;
  public inStock: number;
  public inReserv: number;
  public price: number;
  public stock: Stock;
  public product: Product;
}
