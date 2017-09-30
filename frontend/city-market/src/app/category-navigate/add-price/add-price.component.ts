import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {IPriceService} from '../../services/price/iprice.service';
import {IProductService} from '../../services/product/iproduct.service';
import {Price} from '../../entities/price';
import {Product} from '../../entities/product';
import {IAuthenticationService} from '../../services/authentication/iauthentication.service';
import {Router} from '@angular/router';
import {AccessService} from '../../services/access/access.service';

/**
 * Component that provides the ability to edit the price and the rest of the goods.
 */
@Component({
  selector: 'app-add-price',
  templateUrl: './add-price.component.html',
  styleUrls: ['./add-price.component.css']
})
export class AddPriceComponent implements OnInit {
  /**
   * Price for product existing.
   */
  public price: Price;
  /**
   * New product price.
   */
  public newPrice: number;
  /**
   * Changing the quantity of goods in a warehouse.
   */
  public addProductInStock: number;
  /**
   * Messages to the user during the execution of the application.
   */
  public message: string;
  /**
   * The product over which the work will be carried out.
   */
  public product: Product;

  constructor(@Inject('productService') public productService: IProductService,
              @Inject('priceService') private priceService: IPriceService,
              @Inject('authenticationService') private authService: IAuthenticationService,
              public accessService: AccessService,
              private router: Router) {
  }

  /**
   * A new price is created and the prices for the product are loaded from the database for the
   * product. If there are no prices, a new copy of the price is created.
   */
  ngOnInit() {
    this.price = new Price(0, 0, 0);
    this.product = this.productService.getSelectProduct();
    this.priceService.getPriceByProduct(this.product)
      .then(resp => {
        if (resp.length > 0) this.price = resp[0];
      })
  }

  /**
   * The method sends the service price to the service.
   */
  savePrice() {
    this.price.price = this.newPrice;
    this.price.inStock = this.addProductInStock;
    this.price.product = this.productService.getSelectProduct();
    this.price.stock = this.authService.getUser().stock;
    this.priceService.savePrice(this.price)
      .then(resp => this.message = resp)
      .catch(error => this.message = error);
  }

  /**
   * Return to product viewing.
   */
  closePrice() {
    this.accessService.editPriceAndStock = false;
    this.productService.setSelectProduct(null);
    this.router.navigateByUrl('category/listProduct').then();
  }
}
