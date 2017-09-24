import {Inject, Injectable} from '@angular/core';
import {IOrderService} from './iorder.service';
import {Order} from '../../entity/order';
import {Http, Headers} from '@angular/http';
import {IAuthenticationService} from "../authentication/iauthentication.service";

@Injectable()
export class OrderService implements IOrderService {
  private url = '/CityMarket/api/order';

  constructor(private http: Http,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  getAllOrderToPromise() {
    return new Promise((resolve, reject) => {
      let headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
      const token: string = this.authService.getToken();
      headers.append('X-AUTH-TOKEN', token);
      this.http.get(this.url + 's/market/' + this.authService.getUser().market.id, {headers: headers})
        .subscribe(resp => resolve(resp.json())
          , () => reject('Данные не получены!'));
    });
  }

  saveOrder(order: Order, productId: number, marketId: number) {
    const body = JSON.stringify(order);
    const sendUrl = this.url + '/save/product/' + productId + '/market/' + marketId;
    let headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    return new Promise((resolve, reject) => {
      this.http.post(sendUrl, body, {headers: headers})
        .subscribe(() => resolve('Сохранение прошло успешно!'),
          () => reject('Ошибка записи склада!'));
    });
  }
}
