import {Inject, Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {IMarketService} from '../market/imarket.service';
import {Price} from '../../entity/price';
import {Product} from '../../entity/product';
import {IPriceService} from './iprice.service';
import {IAuthenticationService} from '../authentication/iauthentication.service';

@Injectable()
export class PriceService implements IPriceService {
  private url = '/CityMarket/api/price';

  constructor(private http: Http,
              @Inject('marketService') private marketService: IMarketService,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  getPriceByProductMarket(product: Product) {
    let idMarket = this.marketService.getSelectMarket().id;
    const urlPrices = this.url + 's/product/' + product.id + '/market/' + idMarket;
    return new Promise((response, reject) => {
      this.http.get(urlPrices)
        .subscribe(resp => response(resp.json()),
          error => reject('Ошибка получения цен.'));
    });
  }

  savePrice(price: Price) {
    return new Promise((response, error) => {
      const body = JSON.stringify(price);
      const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
      this.http.post(this.url + '/save', body, {headers: headers})
        .subscribe((resp) => response(resp.json()),
          () => error('Ошибка получения цен!'));
    });
  }

  getPriceByProductStock(product: Product) {
    const url = this.url + 's/product/' + product.id + '/stock/' + this.authService.getUser().stock.id;
    return new Promise((res, rej) => {
      this.http.get(url)
        .subscribe(resp => res(resp.json()),
          error => rej('Ошибка получения цен.'));
    });
  }

}
