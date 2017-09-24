import {Component, Inject, OnInit} from '@angular/core';
import {IProductService} from '../../services/product/iproduct.service';
import {Product} from '../../entity/product';
import {Router} from '@angular/router';
import {IOrderService} from '../../services/order/iorder.service';
import {IMarketService} from '../../services/market/imarket.service';
import {Order} from '../../entity/order';
import {IPriceService} from "../../services/price/iprice.service";
import {Price} from "../../entity/price";
import {Market} from "../../entity/merket";

@Component({
  selector: 'app-order-user',
  templateUrl: './order-user.component.html',
  styleUrls: ['./order-user.component.css']
})
export class OrderUserComponent implements OnInit {
  public order: Order;
  public product: Product;
  public market: Market;
  public message: string;
  public prices: Price[];

  constructor(@Inject('productService') public productService: IProductService,
              @Inject('orderService') public orderService: IOrderService,
              @Inject('marketService') public marketService: IMarketService,
              @Inject('priceService') public priceService: IPriceService,
              private router: Router) {
  }

  ngOnInit() {
    this.order = new Order();
    this.product = this.productService.getSelectProduct();
    this.market = this.marketService.getSelectMarket();
    this.order.status = 'Ожидание';
    this.loadPrice();
  }

  sendOrder() {
    if (this.savePrice())
      this.orderService.saveOrder(this.order, this.product.id, this.market.id)
        .then(resp => this.message = resp)
        .catch(error => this.message = error);
  }

  chancelOrder() {
    this.router.navigateByUrl('category/listProduct');
  }


  savePrice() {
    for (let index = 0; index < this.prices.length; index++) {
      if (this.prices[index].inStock > 0) {
        this.prices[index].inStock = this.prices[index].inStock - 1;
        this.prices[index].inReserv = this.prices[index].inReserv + 1;
        this.priceService.savePrice(this.prices[index]);
        return true;
      }
    }
    return false;
  }

  loadPrice() {
    this.priceService.getPriceByProductMarket(this.product)
      .then(response => this.prices = response)
      .catch(error => this.message = error);
  }
}
