import {Inject, Injectable} from '@angular/core';
import {ICategoryService} from './icategory-service.service';
import {Http} from '@angular/http';
import {Category} from '../../entity/category';
import {Headers} from '@angular/http';
import {IAuthenticationService} from "../authentication/iauthentication.service";

@Injectable()
export class CategoryService implements ICategoryService {
  private url = '/CityMarket/api/categor';
  public categories: Category[];
  private selectCategory: Category;

  constructor(private http: Http,
              @Inject('authenticationService')private authService:IAuthenticationService) {
  }

  getPromiseCategories() {
    return new Promise((resolve, reject) => {
        this.http.get(this.url + 'ies').subscribe((resp) => {
          this.categories = resp.json();
          if (this.categories != null) return resolve(this.categories);
          return reject('error');
        });
      }
    );
  }

  saveCategory(category: Category) {
    const body = JSON.stringify(category);
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    const token: string = this.authService.getToken();
    headers.append('X-AUTH-TOKEN', token);
    return this.http.post(this.url + 'y/save', body, {headers: headers});
  }

  deleteCategory(category: Category) {
    const headers = new Headers({'Content-Type': 'application/json;charset=utf-8'});
    const token: string = this.authService.getToken();
    headers.append('X-AUTH-TOKEN', token);
    return this.http.delete(this.url + 'y/' + category.id, {headers: headers});
  }

  getSelectedCategory() {
    return this.selectCategory;
  }

  setCategory(category: Category) {
    this.selectCategory = category;
  }

  getCategories() {
    return this.categories;
  }
}

