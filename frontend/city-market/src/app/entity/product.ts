import {Stock} from './stock';
import {Category} from './category';
import {Price} from './price';

export class Product {
  public id: number;
  public name: string;
  public stock: Stock;
  public category: Category;
  public price: Price[];
  public priceView: number;
  public inStock: number;
  public inReserv: number;
}
