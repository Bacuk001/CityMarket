import {Inject, Injectable} from '@angular/core';
import {IProductService} from './iproduct.service';
import {Http, Headers} from '@angular/http';
import {ICategoryService} from '../category/icategory-service.service';
import {IMarketService} from '../market/imarket.service';
import {Product} from '../../entity/product';
import {IAuthenticationService} from '../authentication/iauthentication.service';

@Injectable()
export class ProductService implements IProductService {
  private url = '/CityMarket/api/product';
  private products: Product[];
  private selectProduct: Product;

  constructor(private http: Http,
              @Inject('categoryService') private serviceCategory: ICategoryService,
              @Inject('marketService') private serviceMarket: IMarketService,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  getPromiseProducts() {
    return new Promise((resolve, reject) => {
        this.http.get(this.url + 's/market/'
          + this.serviceMarket.getSelectMarket().id + '/category/'
          + this.serviceCategory.getSelectedCategory().id)
          .subscribe((resp) => {
            this.products = resp.json();
            if (this.products != null) return resolve(this.products);
            return reject('error');
          });
      }
    );
  }

  getProducts() {
    return this.products;
  }

  seveProduct(product: Product) {
    alert(product.id);
    const body = JSON.stringify(product, ['name', 'stock', 'name', 'address']);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    return new Promise((resolve, reject) => {
      this.http.post(this.url + '/save/category/'
        + this.serviceCategory.getSelectedCategory().id + '/stock/'
        + this.authService.getUser().stock.id , body, {headers: headers})
        .subscribe(() => resolve('Сохранено'), () => reject('Не сохъранено'));
    });
  }

  getSelectProduyct() {
    return this.selectProduct;
  }

  loadPriseForProducts() {
  }

  getSelectProduct() {
    return this.selectProduct;
  }

  setSelectProduct(product: Product) {
    this.selectProduct = product;
  }

}
