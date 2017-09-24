import {Injectable} from '@angular/core';
import {Headers, Http} from '@angular/http';
import {IMarketService} from './imarket.service';
import {Market} from '../../entity/merket';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class MarketService implements IMarketService {
  private url = '/CityMarket/api/market';
  private markets: Market[];
  private selectMarket;

  constructor(private http: Http) {
  }

  getPromiseMarkets() {
    return new Promise((resolve, reject) => {
      this.http.get(this.url + 's').subscribe((resp) => {
          this.markets = resp.json();
          resolve(this.markets);
        },
        () => reject('Ошибка получения информации о магазинах!'));
    });
  }

  public getSelectMarket() {
    return this.selectMarket;
  }

  public setSelectMarket(market: Market) {
    this.selectMarket = market;
  }

  saveMarket(market: Market) {
    const body = JSON.stringify(market);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    return new Promise((resolve, reject) => {
      this.http.post(this.url + '/save', body, {headers: headers})
        .subscribe(() => resolve('Сохранение прошло успешно!'),
          () => reject('Ошибка записи магазина!'));
    });
  }
}
