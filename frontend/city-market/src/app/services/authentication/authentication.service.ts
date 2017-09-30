import {Inject, Injectable} from '@angular/core';
import {IAuthenticationService} from './iauthentication.service';
import {User} from '../../entities/user';
import {Headers, Http} from '@angular/http';
import {Role} from '../../entities/role';
import {AccessService} from '../access/access.service';
import {IMarketService} from '../market/imarket.service';
import {Router} from "@angular/router";

const ROLE_ADMIN = 'ROLE_ADMIN';
const ROLE_MANAGER_SHOP = 'ROLE_MANAGER_SHOP';
const ROLE_MANAGER_STOCK = 'ROLE_MANAGER_STOCK';
const ERROR_AUTHENTICATION = 'Error authentication!';
const CONTENT_TYPE_VALUE = 'application/json;charset=utf-8';
const HEADERS_PROPERTY_NAME_TOKEN = 'token';
const URL_LOGIN = '/CityMarket/api/Application/login';
const URL_USER = '/CityMarket/api/user';
const URL_ROLE = '/CityMarket/api/role';
const NOT_DOWNLOAD = 'Error download!';
const EMPTY_STRING = '';
const USER_NOT_SAVE = 'User do not save!';
const TOKEN_NAME_FIELD_HEADERS = 'X-AUTH-TOKEN';

/**
 * A class that defines the basic actions for authenticating a user in an application.
 */
@Injectable()
export class AuthenticationService implements IAuthenticationService {
  private token: string;
  private user: User = new User;
  private isSign: boolean;

  constructor(private http: Http, private router: Router,
              private accessService: AccessService,
              @Inject('marketService') private marketService: IMarketService) {
  }

  /**
   * The method returns a token if the user is logged on.
   */
  getToken() {
    return this.token;
  }

  /**
   * The method returns the User that is registered in the system.
   */
  saveUser(user: User) {
    const url = URL_USER + '/save';
    const body = JSON.stringify(user);
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    headers.append(TOKEN_NAME_FIELD_HEADERS, this.token);
    return new Promise((response, reject) =>
      this.http.post(url, body, {headers: headers})
        .subscribe((res) => {
          this.user = res.json();
          return response(this.user);
        }, (error) => reject(USER_NOT_SAVE)));
  }

  /**
   *  The method establishes the user whose it is necessary to remember in the application.
   */
  loginSystem() {
    const body = JSON.stringify(this.user);
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    return new Promise((response, error) =>
      this.http.post(URL_LOGIN, body, {headers: headers}).subscribe(res => {
        if (res.status != 200) return error(ERROR_AUTHENTICATION);
        this.user = res.json();
        this.token = res.headers.get(HEADERS_PROPERTY_NAME_TOKEN);
        this.openComponent();
        return response(this.user);
      }, () => error(ERROR_AUTHENTICATION)));
  }

  /**
   * The method of logging into the system.
   */
  isLoginSystem() {
    return this.isSign;
  }

  /**
   * Method of logging out. The method sets all access values to the application as a user, and
   * erases the registration data.
   */
  getUser() {
    return this.user;
  }

  /**
   * The method returns whether someone is logged on the system.
   */
  logOut() {
    this.isSign = false;
    this.token = EMPTY_STRING;
    this.user = new User;
    this.accessService.logOut();
    this.router.navigateByUrl(EMPTY_STRING).then();
  }

  /**
   * The method returns a list of the role's roles available in the application.
   */
  getRolesToPromise() {
    const url = URL_ROLE + 's';
    return new Promise((resolve, reject) => {
      this.http.get(url).subscribe((resp) => resolve(resp.json()),
        () => reject(NOT_DOWNLOAD));
    });
  }

  /**
   * The method opens and closes the components for users.
   */
  private openComponent() {
    this.isSign = true;
    const roles: Role[] = this.user.roles;
    for (let role of roles) {
      if (role.name === ROLE_ADMIN) this.accessService.openComponentAdmin();
      if (role.name === ROLE_MANAGER_SHOP) this.accessService.openComponentManagerMarket();
      if (role.name === ROLE_MANAGER_STOCK) this.accessService.openComponentManagerStock();
    }
    this.marketService.setSelectMarket(this.user.market);
  }

  /**
   * Users registered in the system.
   *
   * @returns {Promise<any>}
   */
  public getUsers() {
    const url = URL_USER + 's';
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    return new Promise((response, error) =>
      this.http.get(url, {headers: headers}).subscribe(res =>
          response(res.json())
        , () => error(ERROR_AUTHENTICATION)));
  }

  /**
   * The controller method accepts a password request for the user.
   *
   * @param {number} idUser
   * @returns {Promise<any>}
   */
  public getUserPassword(idUser: number) {
    const url = URL_USER + '/' + idUser + '/password'
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    headers.append(TOKEN_NAME_FIELD_HEADERS, this.token);
    return new Promise((response, reject) =>
      this.http.get(url, {headers: headers})
        .subscribe(res => response(res.json()),
          (error) => reject(NOT_DOWNLOAD)))
  }
}
