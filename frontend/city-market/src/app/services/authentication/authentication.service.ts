import {Inject, Injectable} from '@angular/core';
import {IAuthenticationService} from './iauthentication.service';
import {User} from '../../entity/user';
import {Headers, Http} from '@angular/http';
import {Role} from '../../entity/role';
import {AccessService} from '../access/access.service';
import {IMarketService} from '../market/imarket.service';
import {Router} from "@angular/router";


@Injectable()
export class AuthenticationService implements IAuthenticationService {
  private url = '/CityMarket/api/Application/login';
  private urlUser = '/CityMarket/api/user/';
  private urlRole = '/CityMarket/api/role';
  private token: string;
  private user: User = new User;
  private isSign: boolean;

  constructor(private http: Http, private router: Router,
              private accessService: AccessService,
              @Inject('marketService') private marketService: IMarketService) {
    this.token = '';
  }

  getToken() {
    return this.token;
  }

  saveUser(user: User) {
    const url = this.urlUser + 'save';
    const body = JSON.stringify(user);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    headers.append('X-AUTH-TOKEN', this.token);
    return new Promise((response, reject) =>
      this.http.post(url, body, {headers: headers})
        .subscribe((res) => {
          this.user = res.json();
          return response(this.user);
        }, (error) => reject('Пользователь не создан!')));
  }

  loginSystem() {
    const body = JSON.stringify(this.user);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    return new Promise((response, error) =>
      this.http.post(this.url, body, {headers: headers}).subscribe(res => {
        this.user = res.json();
        this.token = res.headers.get('token');
        this.openAndCloseComponent();
        return response(this.user);
      }, () => error('Ошибка регистарции!')));
  }

  isLoginSystem() {
    return this.isSign;
  }

  getUser() {
    return this.user;
  }

  logOut() {
    this.token = '';
    this.openAndCloseComponent();
    this.router.navigateByUrl('').then();
  }

  getRolesToPromise() {
    const url = this.urlRole + 's';
    return new Promise((resolve, reject) => {
      this.http.get(url).subscribe((resp) => resolve(resp.json()),
        () => reject('Данные не получены!'));
    });
  }

  private openAndCloseComponent() {
    this.isSign = !this.isSign;
    const roles: Role[] = this.user.roles;
    for (let role of roles) {
      if (role.name === 'ROLE_ADMIN') this.accessService.inverOpenComponentAdmin();
      if (role.name === 'ROLE_MANAGER_SHOP') this.accessService.inverOpenComponentManagerMarket();
      if (role.name === 'ROLE_MANAGER_STOCK') this.accessService.inverOpenComponentManagerStock();
    }
    this.marketService.setSelectMarket(this.user.market);
  }
}
