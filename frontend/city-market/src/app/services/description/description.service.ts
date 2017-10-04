import {Inject, Injectable} from '@angular/core';
import {IDescriptionService} from "./idescription.service";
import {Product} from "../../entities/product";
import {Description} from "../../entities/description";
import {Http, Headers} from "@angular/http";
import {IAuthenticationService} from "../authentication/iauthentication.service";

const URL: string = '/CityMarket/api/description';
const CONTENT_TYPE_VALUE = 'application/json;charset=utf-8';
const TOKEN_NAME_FIELD_HEADERS = 'X-AUTH-TOKEN';
const ERROR_LOAD_DESCRIPTION = 'Ошибка загрузки описания!';
const SAVE_SUCCESS = 'Сохранено!';
const NOT_SAVE = 'Не сохранено!';


/**
 * Class describes the basic methods with the work of describing products.
 */
@Injectable()
export class DescriptionService implements IDescriptionService {
  constructor(private http: Http,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  /**
   * The method generates a query to obtain descriptions for the goods.
   */
  getDescriptionsByProduct(product: Product) {
    const url = URL + '/product/' + product.id;
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    return new Promise((resolve, reject) => {
      this.http.get(url, {headers: headers})
        .subscribe((res) => resolve(res.json()),
          () => reject(ERROR_LOAD_DESCRIPTION));
    });
  }

  /**
   * The method generates a request to store the goods in the database.
   */
  saveDescription(description: Description[]) {
    const url = URL + '/save';
    const body = JSON.stringify(description);
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    const token: string = this.authService.getToken();
    headers.append(TOKEN_NAME_FIELD_HEADERS, token);
    return new Promise((resolve, reject) => {
      this.http.post(url, body, {headers: headers})
        .subscribe(() => resolve(SAVE_SUCCESS),
          () => reject(NOT_SAVE));
    });
  }
}
