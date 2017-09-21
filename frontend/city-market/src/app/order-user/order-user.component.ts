import {Component, Inject, OnInit} from '@angular/core';
import {IProductService} from '../services/product/iproduct.service';
import {Product} from '../entity/product';
import {Router} from '@angular/router';
import {IOrderService} from '../services/order/iorder.service';
import {IMarketService} from '../services/market/imarket.service';
import {Order} from '../entity/order';

@Component({
  selector: 'app-order-user',
  templateUrl: './order-user.component.html',
  styleUrls: ['./order-user.component.css']
})
export class OrderUserComponent implements OnInit {
  public order: Order;
  public product: Product;
  public message: string;

  constructor(@Inject('productService') public productService: IProductService,
              @Inject('orderService') public orderService: IOrderService,
              @Inject('marketService') public marketService: IMarketService,
              private router: Router) {
  }

  ngOnInit() {
    this.product = this.productService.getSelectProduct();
    this.order = new Order();
    this.order.product = new Array();
    this.order.product.push(this.product);
    this.order.market = this.marketService.getSelectMarket();
    this.order.status = 'Ожидание';
  }

  sendOrder() {
    this.orderService.saveOrder(this.order)
      .then(resp => this.message = resp)
      .catch(error => this.message = error);
  }

  chancelOrder() {
    this.router.navigateByUrl('category/listProduct');
  }

}
