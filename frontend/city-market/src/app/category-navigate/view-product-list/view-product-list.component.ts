import {Component, Inject, OnInit} from '@angular/core';
import {IProductService} from '../../services/product/iproduct.service';
import {AccessService} from '../../services/access/access.service';
import {Router} from '@angular/router';
import {IPriceService} from "../../services/price/iprice.service";
import {Product} from "../../entity/product";


@Component({
  selector: 'app-view-product-list',
  templateUrl: './view-product-list.component.html',
  styleUrls: ['./view-product-list.component.css']
})
export class ViewProductListComponent implements OnInit {
  public products: Product[];
  public message: string;

  constructor(@Inject('productService') public  productService: IProductService,
              @Inject('priceService') public priceService: IPriceService,
              private access: AccessService,
              private router: Router) {
  }

  ngOnInit() {
    this.productService.getPromiseProducts()
      .then(resp => this.products = resp)
      .catch(error => this.message = error)

  }

  selectProduct(product) {
    this.productService.setSelectProduct(product);
    this.access.editPriceAndStock = true;
  }

  checkout(product) {
    this.productService.setSelectProduct(product);
    this.router.navigateByUrl('order');
  }

  viewDescription(product) {
    this.productService.setSelectProduct(product);
    this.router.navigateByUrl('category/viewDescription');
  }

}
