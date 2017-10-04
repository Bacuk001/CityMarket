import {Inject, Injectable} from '@angular/core';
import {ICategoryService} from './icategory-service.service';
import {Http} from '@angular/http';
import {Category} from '../../entities/category';
import {Headers} from '@angular/http';
import {IAuthenticationService} from "../authentication/iauthentication.service";

const ERROR = 'Ошибка загрузки катеории';
const URL = '/CityMarket/api/categor';
const TOKEN_NAME_FIELD_HEADERS = 'X-AUTH-TOKEN';
const CONTENT_TYPE_VALUE = 'application/json;charset=utf-8';

@Injectable()
/**
 * Class defining the basic actions for working with product categories.
 */
export class CategoryService implements ICategoryService {
  public categories: Category[];
  private selectCategory: Category;

  constructor(private http: Http,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  /**
   * The method generates a request for all categories of goods. Returns Promise.
   */
  getPromiseCategories() {
    return new Promise((resolve, reject) => {
      this.http.get(URL + 'ies').subscribe((resp) => {
        this.categories = resp.json();
        if (this.categories != null) return resolve(this.categories);
        return reject(ERROR);
      });
    });
  }

  /**
   * The method generates a query to store the category in the database.
   */
  saveCategory(category: Category) {
    const body = JSON.stringify(category);
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    const token: string = this.authService.getToken();
    headers.append(TOKEN_NAME_FIELD_HEADERS, token);
    return this.http.post(URL + 'y/save', body, {headers: headers});
  }

  /**
   * The method generates a query to remove the category in the database.
   */
  deleteCategory(category: Category) {
    const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
    const token: string = this.authService.getToken();
    headers.append(TOKEN_NAME_FIELD_HEADERS, token);
    return this.http.delete(URL + 'y/' + category.id, {headers: headers});
  }

  getSelectedCategory() {
    return this.selectCategory;
  }

  /**
   * The method sets the category.
   */
  setCategory(category: Category) {
    this.selectCategory = category;
  }

  /**
   * The method returns a list of categories stored in the service.
   */
  getCategories() {
    return this.categories;
  }
}

