import {Inject, Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {IMarketService} from '../market/imarket.service';
import {Price} from '../../entities/price';
import {Product} from '../../entities/product';
import {IPriceService} from './iprice.service';
import {IAuthenticationService} from '../authentication/iauthentication.service';

const URL = '/CityMarket/api/price';
const NOT_DOWNLOAD = 'Ошибка загрузки!';
const CONTENT_TYPE_VALUE = 'application/json;charset=utf-8';
const NOT_SAVE = 'Не сохранено!';

/**
 *  Class describing the basic methods for working with the prices of products.
 */
@Injectable()
export class PriceService implements IPriceService {
  constructor(private http: Http,
              @Inject('marketService') private marketService: IMarketService,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  /**
   *  The method determines for which the rest should be loaded. The remains of a warehouse or
   *  shop. Generates a request for data and requests a return promise.
   */
  getPriceByProduct(product: Product) {
    if (this.marketService.getSelectMarket() != null) return this.getPriceByProductMarket(product);
    if (this.authService.getUser().stock != null) return this.getPriceByProductStock(product);
  }

  /**
   * The method determines the formation of a request to receive balances on the market. Sends a
   * promise and returns.
   *
   * @param {Product} product
   * @returns {Promise<any>}
   */
  private getPriceByProductMarket(product: Product) {
    let idMarket = this.marketService.getSelectMarket().id;
    const urlPrices = URL + 's/product/' + product.id + '/market/' + idMarket;
    return new Promise((response, reject) => {
      this.http.get(urlPrices)
        .subscribe(resp => response(resp.json()),
          error => reject(NOT_DOWNLOAD));
    });
  }

  /**
   *The method generates a query to store the price of the product in the database.
   * @param {Price} price
   */
  savePrice(price: Price) {
    return new Promise((response, error) => {
      const body = JSON.stringify(price);
      const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
      this.http.post(URL + '/save', body, {headers: headers})
        .subscribe((resp) => response(resp.json()),
          () => error(NOT_SAVE));
    });
  }

  /**
   * The method determines the formation of a request to receive balances on the stock. Sends a
   * promise and returns.
   *
   * @param {Product} product
   * @returns {Promise<any>}
   */
  private getPriceByProductStock(product: Product) {
    let stock = this.authService.getUser().stock;
    const url = URL + 's/product/' + product.id + '/stock/' + stock.id;
    return new Promise((response, reject) => {
      this.http.get(url)
        .subscribe(resp => response(resp.json())
          , error => reject(NOT_DOWNLOAD));
    });
  }

}
