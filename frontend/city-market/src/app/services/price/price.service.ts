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
  private price: Price[];

  constructor(private http: Http,
              @Inject('marketService') private marketService: IMarketService,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  getPriceByProductMarket(product: Product) {
    return new Promise((res, rej) => {
      this.http.get(this.url + 's/product/'
        + product.id + '/market/'
        + this.marketService.getSelectMarket().id)
        .subscribe(resp => res(resp.json), error => rej('Ошибка получения цен.'));
    });
  }

  savePrice(price: Price) {
    return new Promise((res, rej) => {
      const body = JSON.stringify(price);
      alert(body);
      const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
      this.http.post(this.url + '/save', body, {headers: headers})
        .subscribe((resp) => {
          this.price = resp.json();
          if (this.price != null) return res(this.price);
          return rej('error');
        });
    });
  }

  getPriceByProductStock(product: Product) {
    return new Promise((res, rej) => {
      this.http.get(this.url + 's/product/'
        + product.id + '/stock/'
        + this.authService.getUser().stock.id)
        .subscribe(resp => res(resp.json()), error => rej('Ошибка получения цен.'));
    });
  }

}
