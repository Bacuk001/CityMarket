import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {IPriceService} from '../../services/price/iprice.service';
import {IProductService} from '../../services/product/iproduct.service';
import {Price} from '../../entity/price';
import {Product} from '../../entity/product';
import {IAuthenticationService} from '../../services/authentication/iauthentication.service';
import {Router} from '@angular/router';
import {AccessService} from '../../services/access/access.service';

@Component({
  selector: 'app-add-price',
  templateUrl: './add-price.component.html',
  styleUrls: ['./add-price.component.css']
})
export class AddPriceComponent implements OnInit {
  public price: Price;
  public newPrice: number;
  public addProduct: number;

  constructor(@Inject('productService') public productService: IProductService,
              @Inject('priceService') private priceService: IPriceService,
              @Inject('authenticationService') private authService: IAuthenticationService,
              public accessService: AccessService,
              private router: Router) {
  }

  ngOnInit() {
    this.price = new Price();
    this.priceService.getPriceByProductStock(this.productService.getSelectProduct())
      .then(resp => {
        if (resp !== null) this.price = resp;
        this.price.product = this.productService.getSelectProduct();
        this.price.stock = this.authService.getUser().stock;
      })
      .catch(() => this.price = new Price());
  }

  savePrice() {
    this.price.price = this.newPrice;
    this.price.inStock = this.addProduct;
    this.priceService.savePrice(this.price);
    this.router.navigateByUrl('category');
  }

  closePrice() {
    this.accessService.editPriceAndStock = false;
    this.productService.setSelectProduct(null);
    this.router.navigateByUrl('category/listProduct');
  }
}
