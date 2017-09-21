import {Inject, Injectable} from '@angular/core';
import {IAuthenticationService} from './iauthentication.service';
import {User} from '../../entity/user';
import {Http, Headers} from '@angular/http';
import {Role} from '../../entity/role';
import {AccessService} from '../access/access.service';
import {ICategoryService} from '../category/icategory-service.service';
import {IMarketService} from '../market/imarket.service';

@Injectable()
export class AuthenticationService implements IAuthenticationService {
  private url = '/CityMarket/api/Application/login';
  private urlUser = '/CityMarket/api/user/';
  private urlRole = '/CityMarket/api/role';
  private token: string;
  private user: User = new User;
  private isSign: boolean;

  constructor(private http: Http,
              private accessService: AccessService,
              @Inject('marketService') private marketService: IMarketService) {
  }

  getToken() {
    return this.token;
  }

  saveUser(user: User) {
    const body = JSON.stringify(user);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    this.http.post(this.urlUser + 'save', body, {headers: headers}).subscribe((res) => {
      this.user = res.json();
    });
  }

  loginSystem() {
    const body = JSON.stringify(this.user);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    return new Promise((resp, error) =>
      this.http.post(this.url + '', body, {headers: headers}).subscribe(res => {
          this.user = res.json();
          this.token = res.headers.get('token');
          this.isSign = true;
          this.openComponent();
          return resp(this.user);
        }, () => 'Ошибка регистарции!'
      ));
  }

  isLoginSystem() {
    return this.isSign;
  }

  getUser() {
    return this.user;
  }

  logOut() {
    this.token = '';
    this.isSign = false;
    this.closeComponent();
  }

  getRolesToPromise() {
    return new Promise((resolve, reject) => {
      this.http.get(this.urlRole + 's')
        .subscribe((resp) => resolve(resp.json()),
          () => reject('Данные не получены!'));
    });
  }

  private openComponent() {
    this.accessService.selectMarket = false;
    this.accessService.productCreate = true;
    this.accessService.categoryCreate = true;
    this.marketService.setSelectMarket(this.user.market);
  }

  private closeComponent() {
    this.accessService.selectMarket = true;
    this.marketService.setSelectMarket(null);
  }
}
