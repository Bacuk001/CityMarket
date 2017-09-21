import {Product} from '../../entity/product';

export interface IProductService {
  getPromiseProducts();

  getProducts();

  seveProduct(product: Product);

  getSelectProduyct();

  loadPriseForProducts();

  getSelectProduct();

  setSelectProduct(product: Product);

}
