import {Inject, Injectable} from '@angular/core';
import {IStockService} from './istock.service';
import {Stock} from '../../entities/stock';
import {Http, Headers} from '@angular/http';
import {Market} from '../../entities/merket';
import {IAuthenticationService} from "../authentication/iauthentication.service";

const URL = '/CityMarket/api/stock';
const TOKEN_NAME_FIELD_HEADERS = 'X-AUTH-TOKEN';
const STOCK_NOT_SAVE = 'Stock do not save!';
const SAVE_SUCCESS = 'The saving was successful!';
const SIGN_SUCCESS = 'The sign was successful!';
const NOT_DOWNLOAD = 'Error download!';
const CONTENT_TYPE_VALUE = 'application/json;charset=utf-8';

/**
 * Class for working with product storage.
 */
@Injectable()
export class StockService implements IStockService {
  private stock: Stock[];
  private selectedStock: Stock;

  constructor(private http: Http,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  /**
   * The method saves the created store.
   *
   * @param {Stock} stock
   * @returns {Promise<any>}
   */
  saveStock(stock: Stock) {
    const body = JSON.stringify(stock);
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    const token: string = this.authService.getToken();
    headers.append(TOKEN_NAME_FIELD_HEADERS, token);
    return new Promise((resolve, reject) => {
      this.http.post(URL + '/save', body, {headers: headers})
        .subscribe(() => resolve(SAVE_SUCCESS),
          () => reject(STOCK_NOT_SAVE));
    });
  }

  /**
   * The method creates a query in the database to obtain a list of warehouses.
   *
   * @returns {Promise<any>}
   */
  getStocksToPromise() {
    return new Promise((resolve, reject) => {
      this.http.get(URL + 's')
        .subscribe((resp) => {
          this.stock = resp.json();
          resolve(this.stock);
        }, () => reject(NOT_DOWNLOAD));
    });
  }

  /**
   * The method returns the stock registered for the actions.
   *
   * @returns {Stock}
   */
  getSelectedStock() {
    return this.selectedStock;
  }

  /**
   * The method establishes a warehouse for the work to be carried out.
   *
   * @param {Stock} stock
   */
  setSelectedStock(stock: Stock) {
    this.selectedStock = stock;
  }

  /**
   * The method forms a query in the database to obtain all the warehouses to which the market is
   * subscribed.
   *
   * @param {Market} market
   * @returns {Promise<any>}
   */
  getStocksByMarket(market: Market) {
    return new Promise((resolve, reject) => {
      this.http.get(URL + 's/market/' + market.id)
        .subscribe((resp) => resolve(resp.json()),
          () => reject(NOT_DOWNLOAD));
    });
  }

  /**
   * The method signs the store on the storage list.
   *
   * @param {Market} market
   * @param {Stock[]} stock
   * @returns {Promise<any>}
   */
  signStockMarket(market: Market, stock: Stock[]) {
    const body = JSON.stringify(stock);
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    const token: string = this.authService.getToken();
    headers.append(TOKEN_NAME_FIELD_HEADERS, token);
    return new Promise((resolve, reject) => {
      this.http.post(URL + '/sign/market/' + market.id, body, {headers: headers})
        .subscribe(() => resolve(SIGN_SUCCESS),
          () => reject(NOT_DOWNLOAD));
    });
  }
}

