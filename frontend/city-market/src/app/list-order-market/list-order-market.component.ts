import {Component, Inject, OnInit} from '@angular/core';
import {Order} from "../entity/order";
import {IOrderService} from "../services/order/iorder.service";
import {Product} from "../entity/product";
import {IPriceService} from "../services/price/iprice.service";
import {IAuthenticationService} from "../services/authentication/iauthentication.service";
import {Market} from "../entity/merket";
import {Price} from "../entity/price";

@Component({
  selector: 'app-list-order-market',
  templateUrl: './list-order-market.component.html',
  styleUrls: ['./list-order-market.component.css']
})
export class ListOrderMarketComponent implements OnInit {
  public orders: Order[];
  public products: Product[];
  public message: string;
  public statusList: string[];
  public status: string;
  private market: Market;

  constructor(@Inject('orderService') private orderService: IOrderService,
              @Inject('authenticationService') private authService: IAuthenticationService,
              @Inject('priceService') private priceService: IPriceService) {
    this.statusList = new Array();
    this.statusList.push('OK');
    this.statusList.push('DENIED');
  }

  ngOnInit() {
    this.market = this.authService.getUser().market;
    this.orderService.getAllOrderToPromise()
      .then(resp => this.orders = resp)
      .catch(error => this.message = error)
  }

  selectStatus(order) {
    if (order.status === 'OK') this.getPriseProduct(order.product);
    this.orderService.saveOrder(order, order.product[0].id, this.market.id)
  }

  getPriseProduct(product: Product[]) {
    for (let index = 0; index < product.length; index++)
      this.priceService.getPriceByProductMarket(product[index])
        .then(response => this.deleteReservProduct(response))
  }

  deleteReservProduct(price: Price[]) {
    for (let index = 0; index < price.length; index++) {
      if (price[index].inReserv > 0) {
        price[index].inReserv = price[index].inReserv - 1;
        this.savePrise(price[index]);
        return true;
      }
    }
    return false;
  }

  savePrise(price: Price) {
    this.priceService.savePrice(price);
  }
}
