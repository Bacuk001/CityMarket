import {Injectable} from '@angular/core';

@Injectable()
export class AccessService {
  public productCreate: boolean;
  public categoryCreate: boolean;
  public selectMarket: boolean;
  public editPriceAndStock: boolean;
  public signStock: boolean;
  public createUser: boolean;
  public createShop: boolean;
  public orderList: boolean;
  public viewCategorySelect: boolean;

  constructor() {
    this.selectMarket = true;
    this.productCreate = false;
    this.categoryCreate = false;
    this.editPriceAndStock = false;
    this.signStock = false;
    this.createUser = false;
    this.orderList = false;
    this.viewCategorySelect = false;
  }

  inverOpenComponentManagerStock() {
    this.productCreate = !this.productCreate;
    this.categoryCreate = !this.categoryCreate;
    this.selectMarket = !this.selectMarket ;

  }

  inverOpenComponentManagerMarket() {
    this.signStock = !this.signStock;
    this.orderList = !this.orderList;
    this.viewCategorySelect = !this.viewCategorySelect;
    this.selectMarket = !this.selectMarket ;
  }

  inverOpenComponentAdmin() {
    this.createUser = !this.createUser;
    this.createShop = !this.createShop;
  }

}
