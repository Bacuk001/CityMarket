import {Injectable} from '@angular/core';
import {IOrderService} from './iorder.service';
import {Order} from '../../entity/order';
import {Http, Headers} from '@angular/http';

@Injectable()
export class OrderService implements IOrderService {
  private url = '/CityMarket/api/order';

  constructor(private http: Http) {
  }

  getAllOrderToPromise() {
    return new Promise((resolve, reject) => {
      this.http.get(this.url + 's')
        .subscribe(resp => resolve(resp.json())
          , () => reject('Данные не получены!'));
    });
  }

  saveOrder(order: Order) {
    const body = JSON.stringify(order);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    return new Promise((resolve, reject) => {
      this.http.post(this.url + '/save', body, {headers: headers})
        .subscribe(() => resolve('Сохранение прошло успешно!'),
          () => reject('Ошибка записи склада!'));
    });
  }
}
