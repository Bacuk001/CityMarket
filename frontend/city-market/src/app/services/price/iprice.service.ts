import {Product} from '../../entity/product';
import {Price} from '../../entity/price';

export interface IPriceService {
  getPriceByProductMarket(product: Product);

  savePrice(price: Price);

  getPriceByProductStock(product: Product);
}
