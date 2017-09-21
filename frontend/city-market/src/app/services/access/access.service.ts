import {Injectable} from '@angular/core';

@Injectable()
export class AccessService {
  public productCreate: boolean;
  public categoryCreate: boolean;
  public selectMarket: boolean;
  public editPriceAndStock: boolean;

  constructor() {
    this.productCreate = false;
    this.categoryCreate = false;
    this.selectMarket = true;
    this.editPriceAndStock = false;
  }

}
