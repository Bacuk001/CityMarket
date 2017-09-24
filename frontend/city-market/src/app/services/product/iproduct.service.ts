import {Product} from '../../entity/product';

export interface IProductService {
  getPromiseProducts();

  getProducts();

  saveProduct(product: Product);

  getSelectProduct();

  loadPriseForProducts();

  getSelectProduct();

  setSelectProduct(product: Product);


  loadPrice(products: Product[]);
}
