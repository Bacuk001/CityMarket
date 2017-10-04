import {Injectable} from '@angular/core';

/**
 * It contains information about the availability of components in the application for specific
 * roles.
 */

@Injectable()
export class AccessService {
  /**
   * Determines access to the component to create the product.
   */
  public productCreate: boolean;
  /**
   * Specifies access to the component to create a product category.
   */
  public categoryCreate: boolean;
  /**
   * Defines access to the market selection component.
   */
  public selectMarket: boolean;
  /**
   * Specifies access to the component to change the price and the remainder of the goods in the
   * store.
   */
  public editPriceAndStock: boolean;
  /**
   * Specifies access to the store subscription component for storage.
   */
  public signStock: boolean;
  /**
   * Specifies access to the component to create the user.
   */
  public createUser: boolean;
  /**
   * Defines access to the component to create a market.
   */
  public createShop: boolean;
  /**
   * Defines access to the component to create a market.
   */
  public createStock: boolean;
  /**
   * Specifies access to the component that processes orders from users.
   */
  public orderList: boolean;
  /**
   * Specifies access to the category selection component.
   */
  public viewCategorySelect: boolean;
  /**
   * Navigation menu for the administrator.
   */
  public adminBar: boolean;
  /**
   * Navigation menu for the manager shop.
   */
  public managerShopBar: boolean;
  /**
   * Navigation menu for the manager stock.
   */
  public managerStockBar: boolean;

  /**
   * The designer determines which components are available when entering the application, which
   * are not.
   */
  constructor() {
    this.selectMarket = true;
    this.productCreate = false;
    this.categoryCreate = false;
    this.editPriceAndStock = false;
    this.signStock = false;
    this.createUser = false;
    this.orderList = false;
    this.viewCategorySelect = false;
    this.createStock = false;
    this.adminBar = false;
    this.managerShopBar = false;
    this.managerStockBar = false;
  }

  /**
   * The class method provides access to the components that are available for the storage manager.
   */
  openComponentManagerStock() {
    this.productCreate = true;
    this.categoryCreate = true;
    this.selectMarket = false;
    this.viewCategorySelect = true;
    this.managerStockBar = true;
  }

  /**
   * The class method provides access to components that are available to the market manager.
   */
  openComponentManagerMarket() {
    this.signStock = true;
    this.orderList = true;
    this.viewCategorySelect = true;
    this.selectMarket = false;
    this.managerShopBar = true;
  }

  /**
   * The class method provides access to components that are available to the administrator.
   */
  openComponentAdmin() {
    this.createUser = true;
    this.createShop = true;
    this.createStock = true;
    this.adminBar = true;
  }

  /**
   * The class method provides access to components that are accessible to users and closes
   * everything for administrators.
   */
  logOut() {
    this.selectMarket = true;
    this.productCreate = false;
    this.categoryCreate = false;
    this.editPriceAndStock = false;
    this.signStock = false;
    this.createUser = false;
    this.orderList = false;
    this.viewCategorySelect = false;
    this.createStock = false;
    this.createShop = false;
    this.adminBar = false;
    this.managerShopBar = false;
    this.managerStockBar = false;
  }

}
