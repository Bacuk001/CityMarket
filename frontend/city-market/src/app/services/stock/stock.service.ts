import {Injectable} from '@angular/core';
import {IStockService} from './istock.service';
import {Stock} from '../../entity/stock';
import {Http, Headers} from '@angular/http';
import {Market} from '../../entity/merket';

@Injectable()
export class StockService implements IStockService {
  private url = '/CityMarket/api/stock';
  private stock: Stock[];
  private selectedStock: Stock;

  constructor(private http: Http) {
  }

  saveStock(stock: Stock) {
    const body = JSON.stringify(stock);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    return new Promise((resolve, reject) => {
      this.http.post(this.url + '/save', body, {headers: headers})
        .subscribe(() => resolve('Сохранение прошло успешно!'),
          () => reject('Ошибка записи склада!'));
    });
  }

  getStocksToPromise() {
    return new Promise((resolve, reject) => {
      this.http.get(this.url + 's')
        .subscribe((resp) => {
          this.stock = resp.json();
          resolve(this.stock);
        }, () => reject('Данные не получены!'));
    });
  }

  getSelectedStock() {
    return this.selectedStock;
  }

  setSelectedStock(stock: Stock) {
    this.selectedStock = stock;
  }

  getStocksByMarket(market: Market) {
    return new Promise((resolve, reject) => {
      this.http.get(this.url + 's/market/' + market.id)
        .subscribe((resp) => resolve(resp.json()),
          () => reject('Данные не получены!'));
    });
  }

  signStockMarket(market: Market, stock: Stock[]) {
    const body = JSON.stringify(stock);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    return new Promise((resolve, reject) => {
      this.http.post(this.url + '/sign/market/' + market.id, body, {headers: headers})
        .subscribe(() => resolve('Подпись на склады прошло успешно!'),
          () => reject('Ошибка записи склада!'));
    });
  }
}

