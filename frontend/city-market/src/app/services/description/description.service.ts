import {Inject, Injectable} from '@angular/core';
import {IDescriptionService} from "./idescription.service";
import {Product} from "../../entity/product";
import {Description} from "../../entity/description";
import {Http, Headers} from "@angular/http";
import {IAuthenticationService} from "../authentication/iauthentication.service";

@Injectable()
export class DescriptionService implements IDescriptionService {
  private url: string = '/CityMarket/api/description';

  constructor(private http: Http,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  getDescriptionByProduct(product: Product) {
    const url = this.url + '/product/' + product.id;
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    return new Promise((resolve, reject) => {
      this.http.get(url, {headers: headers})
        .subscribe((res) => resolve(res.json()),
          () => reject('Ошибка получения информации!'));
    });
  }

  saveDescription(description: Description[]) {
    const url = this.url + '/save';
    const body = JSON.stringify(description);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    const token: string = this.authService.getToken();
    headers.append('X-AUTH-TOKEN', token);
    return new Promise((resolve, reject) => {
      this.http.post(url, body, {headers: headers})
        .subscribe(() => resolve('Сохранение прошло успешно!'),
          () => reject('Ошибка записи склада!'));
    });
  }
}
