import {Inject, Injectable} from '@angular/core';
import {IProductService} from './iproduct.service';
import {Http, Headers} from '@angular/http';
import {ICategoryService} from '../category/icategory-service.service';
import {IMarketService} from '../market/imarket.service';
import {Product} from '../../entity/product';
import {IAuthenticationService} from '../authentication/iauthentication.service';
import {IPriceService} from "../price/iprice.service";
import {stringDistance} from "codelyzer/util/utils";

@Injectable()
export class ProductService implements IProductService {
  private url = '/CityMarket/api/product';
  private products: Product[];
  private selectProduct: Product;

  constructor(private http: Http,
              @Inject('categoryService') private serviceCategory: ICategoryService,
              @Inject('marketService') private serviceMarket: IMarketService,
              @Inject('authenticationService') private authService: IAuthenticationService,
              @Inject('priceService') private priceService: IPriceService) {
  }

  getPromiseProducts() {
    const user = this.authService.getUser();
    if (user.market !== null) {
      const url = this.url + 's/market/' + user.market.id + '/category/'
        + this.serviceCategory.getSelectedCategory().id;
      return this.loadProduct(url);
    }
    if (user.stock !== null) {
      const url = this.url + 's/stock/' + user.stock.id + '/category/'
        + this.serviceCategory.getSelectedCategory().id;
      return this.loadProduct(url);
    }
    if (this.serviceMarket !== null) {
      const url = this.url + 's/market/' + this.serviceMarket.getSelectMarket().id + '/category/'
        + this.serviceCategory.getSelectedCategory().id;
      return this.loadProduct(url);
    }
  }

  private loadProduct(url: string) {
    return new Promise((resolve, reject) => {
      this.http.get(url)
        .subscribe(resp => {
          this.products = resp.json();
          this.loadPrice();
          return resolve(this.products);
        }, error => reject('Ошибка загрузки продуктов!'));
    });
  }

  getProducts() {
    return this.products;
  }

  saveProduct(product: Product) {
    let url = this.url + '/save/category/' + this.serviceCategory.getSelectedCategory().id + '/stock/'
      + this.authService.getUser().stock.id;
    alert(product.id);
    const body = JSON.stringify(product, ['name', 'stock', 'name', 'address']);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    const token: string = this.authService.getToken();
    headers.append('X-AUTH-TOKEN', token);
    return new Promise((resolve, reject) => {
      this.http.post(url, body, {headers: headers})
        .subscribe(() => resolve('Сохранено'), () => reject('Не сохъранено'));
    });
  }

  loadPriseForProducts() {
  }

  getSelectProduct() {
    return this.selectProduct;
  }

  setSelectProduct(product: Product) {
    this.selectProduct = product;
  }

  loadPrice() {
    for (let index = 0; index < this.products.length; index++) {
      this.priceService.getPriceByProductMarket(this.products[index])
        .then(resp => this.products[index].price = resp)
    }
  }
}
