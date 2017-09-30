import {Inject, Injectable} from '@angular/core';
import {IProductService} from './iproduct.service';
import {Http, Headers} from '@angular/http';
import {ICategoryService} from '../category/icategory-service.service';
import {IMarketService} from '../market/imarket.service';
import {Product} from '../../entities/product';
import {IAuthenticationService} from '../authentication/iauthentication.service';
import {IPriceService} from "../price/iprice.service";
import {stringDistance} from "codelyzer/util/utils";
import {resolveReflectiveProviders} from "@angular/core/src/di/reflective_provider";
import {Price} from "../../entities/price";

const URL = '/CityMarket/api/product';
const STATUS_SAVE_SUCCESS = 'Save successfully!';
const CONTENT_TYPE_VALUE = 'application/json;charset=utf-8';
const TOKEN_NAME_FIELD_HEADERS = 'X-AUTH-TOKEN';
const ERROR_LOAD = 'Error load product!';
const NOT_SAVE = 'Description do not save!'

/**
 * The class that defines the methods of interaction with products in the application.
 */
@Injectable()
export class ProductService implements IProductService {
  private products: Product[];
  private selectProduct: Product;
  private countPageProduct: number[];

  constructor(private http: Http,
              @Inject('categoryService') private serviceCategory: ICategoryService,
              @Inject('marketService') private serviceMarket: IMarketService,
              @Inject('authenticationService') private authService: IAuthenticationService,
              @Inject('priceService') private priceService: IPriceService) {
  }

  /**
   * The method sends a request for all products.
   */
  getAllProducts() {
    const category = this.serviceCategory.getSelectedCategory();
    let url = URL + 's/category/' + category.id;
    return new Promise((resolve, reject) => {
      this.http.get(url)
        .subscribe(resp => resolve(resp.json())
          , error => reject(ERROR_LOAD));
    });
  }

  /**
   * The method determines whether to load products by the warehouse or the store, forms a request
   * and sends it to receive the products. Returns Promise.
   *
   * @param {number} page
   * @returns {Promise<any>}
   */
  getPromiseProducts(page: number) {
    const user = this.authService.getUser();
    if (user.stock != null) {
      const category = this.serviceCategory.getSelectedCategory();
      const url = URL + 's/stock/' + user.stock.id + '/category/' + category.id;
      return this.loadProduct(url);
    }
    if (this.serviceMarket != null) {
      const market = this.serviceMarket.getSelectMarket();
      const category = this.serviceCategory.getSelectedCategory();
      const url = URL + 's/market/' + market.id + '/category/' + category.id + '/sizePage/10/page/' + page;
      return this.loadProduct(url);
    }
  }

  /**
   * The method generates and sends a request to receive products.
   * @param {string} url
   * @returns {Promise<any>}
   */
  private loadProduct(url: string) {
    return new Promise((resolve, reject) => {
      this.http.get(url)
        .subscribe(resp => {
          this.products = resp.json();
          this.loadPrice(this.products);
          this.downloadCountProduct();
          return resolve(this.products);
        }, error => reject(ERROR_LOAD));
    });
  }

  /**
   * The method generates and sends a request to obtain the number of products.
   *
   * @returns {Promise<any>}
   */
  loadCountProduct() {
    return this.countPageProduct;
  }

  /**
   * The method counts the number of pages.
   *
   * @param {number} count
   */
  counterPage(count: number) {
    let countPages = Math.trunc(count / 10);
    if (count % 10 > 0) countPages++;
    this.countPageProduct = new Array(countPages);
  }

  /**
   * The method returns a list of products stored in the service.
   *
   * @returns {Product[]}
   */
  getProducts() {
    return this.products;
  }

  /**
   * The method generates a request to save the product and sends it for storage.
   *
   * @param {Product} product
   * @returns {Promise<any>}
   */
  saveProduct(product: Product) {
    const category = this.serviceCategory.getSelectedCategory();
    const stock = this.authService.getUser().stock;
    let url = URL + '/save/category/' + category.id + '/stock/' + stock.id;
    const body = JSON.stringify(product);
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    const token: string = this.authService.getToken();
    headers.append(TOKEN_NAME_FIELD_HEADERS, token);
    return new Promise((resolve, reject) => {
      this.http.post(url, body, {headers: headers})
        .subscribe(() => resolve(STATUS_SAVE_SUCCESS),
          () => reject(NOT_SAVE));
    });
  }

  /**
   * The method returns the product that was selected for the operations.
   *
   * @returns {Product}
   */
  getSelectProduct() {
    return this.selectProduct;
  }

  /**
   * Establishes the product over which the actions will be performed.
   *
   * @param {Product} product
   */
  setSelectProduct(product: Product) {
    this.selectProduct = product;
  }

  /**
   * The method takes a list of products and loads the prices.
   *
   * @param {Product[]} products
   */
  loadPrice(products: Product[]) {
    for (let product of products) {
      this.priceService.getPriceByProduct(product)
        .then(resp => this.selectionOfPrice(product, resp))
    }
  }

  /**
   * The method determines the first price that is in the warehouses and assigns a value to the
   * field that is displayed to the buyer.
   */
  selectionOfPrice(product: Product, prices: Price[]) {
    product.price = prices;
    for (let price of prices) {
      if (price.inStock > 0) {
        product.priceView = price.price.toString();
        return;
      }
    }
    product.priceView = 'Нет в наличии';
  }

  /**
   * Method requests from the server the quantity of goods in the warehouse.
   */
  private downloadCountProduct() {
    const market = this.serviceMarket.getSelectMarket();
    const category = this.serviceCategory.getSelectedCategory();
    let url = URL + 's/market/' + market.id + '/category/' + category.id + '/count';
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    this.http.get(url, {headers: headers})
      .subscribe(resp => this.counterPage(resp.json()));
  }
}
