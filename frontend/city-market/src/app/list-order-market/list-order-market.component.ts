import {Component, Inject, OnInit} from '@angular/core';
import {Order} from "../entities/order";
import {IOrderService} from "../services/order/iorder.service";
import {Product} from "../entities/product";
import {IPriceService} from "../services/price/iprice.service";
import {IAuthenticationService} from "../services/authentication/iauthentication.service";
import {Market} from "../entities/merket";
import {Price} from "../entities/price";

const DENIED = 'отказано';
const OK = 'одобрено';

/**
 * Component for processing orders in the store.
 */
@Component({
  selector: 'app-list-order-market',
  templateUrl: './list-order-market.component.html',
  styleUrls: ['./list-order-market.component.css']
})
export class ListOrderMarketComponent implements OnInit {
  /**
   * List of orders in the store.
   */
  public orders: Order[];
  /**
   * Messages that will be sent to the user while using the application.
   */
  public message: string;
  /**
   * The status of the selected order.
   */
  public statusList: string[];
  /**
   * List of possible types of order statuses.
   */
  private market: Market;

  constructor(@Inject('orderService') private orderService: IOrderService,
              @Inject('authenticationService') private authService: IAuthenticationService,
              @Inject('priceService') private priceService: IPriceService) {
    this.statusList = new Array();
    this.statusList.push(OK);
    this.statusList.push(DENIED);
  }

  /**
   * TThe method is executed when the component is initialized. we receive from the registration
   * service the market over which the user is working. and load orders in the store.
   */
  ngOnInit() {
    this.market = this.authService.getUser().market;
    this.orderService.getAllOrderToPromise()
      .then(resp => this.orders = resp)
      .catch(error => this.message = error)
  }

  /**
   * The method changes the status of the order.
   *
   * @param order
   */
  selectStatus(order) {
    if (order.status == OK) this.getPriseProduct(order.product, true);
    if (order.status == DENIED) this.getPriseProduct(order.product, true);
    this.orderService.saveOrder(order, order.product[0].id, this.market.id)
  }

  /**
   * Loading of prices by warehouse for goods.
   *
   * @param {Product[]} product
   */
  getPriseProduct(product: Product[], isSaleProduct: boolean) {
    for (let index = 0; index < product.length; index++)
      this.priceService.getPriceByProduct(product[index])
        .then(response => {
          if (isSaleProduct) this.saleProduct(response);
          if (!isSaleProduct) this.denialSaleProduct(response);
        })
  }

  /**
   * Removes the reserved unit from the price.
   *
   * @param {Price[]} price
   * @returns {boolean}
   */
  private saleProduct(price: Price[]) {
    for (let index = 0; index < price.length; index++) {
      if (price[index].inReserv > 0) {
        price[index].inReserv = price[index].inReserv - 1;
        this.savePrise(price[index]);
        return true;
      }
    }
    return false;
  }

  /**
   * The method removes the unit of the goods from the reserve, if the seller refuses to sell.
   * @param {Price[]} price
   * @returns {boolean}
   */
  private denialSaleProduct(price: Price[]) {
    for (let index = 0; index < price.length; index++) {
      if (price[index].inReserv > 0) {
        price[index].inReserv = price[index].inReserv - 1;
        price[index].inStock = price[index].inStock + 1;
        this.savePrise(price[index]);
        return true;
      }
    }
    return false;
  }

  /**
   * Keeps the price.
   *
   * @param {Price} price
   */
  savePrise(price: Price) {
    this.priceService.savePrice(price);
  }

}
