import {Inject, Injectable} from '@angular/core';
import {IOrderService} from './iorder.service';
import {Order} from '../../entities/order';
import {Http, Headers} from '@angular/http';
import {IAuthenticationService} from "../authentication/iauthentication.service";

const URL = '/CityMarket/api/order';
const CONTENT_TYPE_VALUE = 'application/json;charset=utf-8';
const TOKEN_NAME_FIELD_HEADERS = 'X-AUTH-TOKEN';
const SAVE_SUCCESS = 'Сохранено!';
const NOT_SAVE = 'Не сохранено!';
const ERROR_LOAD = 'Ошибка загрузки!';

/**
 * Class describing methods for working with product orders.
 */
@Injectable()
export class OrderService implements IOrderService {

  constructor(private http: Http,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  /**
   * The method generates a request to receive all orders.
   */
  getAllOrderToPromise() {
    return new Promise((resolve, reject) => {
      let headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
      const token: string = this.authService.getToken();
      headers.append(TOKEN_NAME_FIELD_HEADERS, token);
      this.http.get(URL + 's/market/' + this.authService.getUser().market.id, {headers: headers})
        .subscribe(resp => resolve(resp.json())
          , () => reject(ERROR_LOAD));
    });
  }

  /**
   * The method generates a request to save the order. The method takes the order, the maize in
   * which the order and the product id are made which is in order.
   */
  saveOrder(order: Order, productId: number, marketId: number) {
    const body = JSON.stringify(order);
    const sendUrl = URL + '/save/product/' + productId + '/market/' + marketId;
    let headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    return new Promise((resolve, reject) => {
      this.http.post(sendUrl, body, {headers: headers})
        .subscribe(() => resolve(SAVE_SUCCESS),
          () => reject(NOT_SAVE));
    });
  }
}
