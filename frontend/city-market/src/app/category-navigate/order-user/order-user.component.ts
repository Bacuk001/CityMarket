import {Component, Inject, OnInit} from '@angular/core';
import {IProductService} from '../../services/product/iproduct.service';
import {Product} from '../../entities/product';
import {Router} from '@angular/router';
import {IOrderService} from '../../services/order/iorder.service';
import {IMarketService} from '../../services/market/imarket.service';
import {Order} from '../../entities/order';
import {IPriceService} from "../../services/price/iprice.service";
import {Price} from "../../entities/price";
import {Market} from "../../entities/merket";

@Component({
  selector: 'app-order-user',
  templateUrl: './order-user.component.html',
  styleUrls: ['./order-user.component.css']
})
export class OrderUserComponent implements OnInit {
  /**
   * Order to be sent for processing.
   */
  public order: Order;
  /**
   * Product that will be in order.
   */
  public product: Product;
  /**
   * Shop in which it was decorated.
   */
  public market: Market;
  /**
   * Messages for the user during the application process.
   */
  public message: string;
  /**
   *  Product prices.
   */
  public prices: Price[];

  constructor(@Inject('productService') public productService: IProductService,
              @Inject('orderService') public orderService: IOrderService,
              @Inject('marketService') public marketService: IMarketService,
              @Inject('priceService') public priceService: IPriceService,
              private router: Router) {
  }

  /**
   * When the component is initialized, all the variables required for the order are initialized.
   * Value is taken from the services.
   */
  ngOnInit() {
    this.order = new Order();
    this.product = this.productService.getSelectProduct();
    this.market = this.marketService.getSelectMarket();
    this.order.status = 'Ожидание';
    this.loadPrice();
  }

  /**
   * The method sends an order to the service, for storage.
   */
  sendOrder() {
    if (this.savePrice())
      this.orderService.saveOrder(this.order, this.product.id, this.market.id)
        .then(resp => this.message = resp)
        .catch(error => this.message = error);
  }

  /**
   * The method leaves the order.
   */
  chancelOrder() {
    this.router.navigateByUrl('category/listProduct');
  }

  /**
   * The method removes one unit of goods from the remainders and adds this unit to the reserve.
   *
   * @returns {boolean}
   */
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

  /**
   * The method looks at the products received and accesses the service to load product prices.
   *
   * @param {Product[]} products
   */
  loadPrice() {
    this.priceService.getPriceByProduct(this.product)
      .then(response => this.prices = response)
      .catch(error => this.message = error);
  }
}
