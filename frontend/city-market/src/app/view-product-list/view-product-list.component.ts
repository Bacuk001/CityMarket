import {Component, Inject} from '@angular/core';
import {IProductService} from '../services/product/iproduct.service';
import {AccessService} from '../services/access/access.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-view-product-list',
  templateUrl: './view-product-list.component.html',
  styleUrls: ['./view-product-list.component.css']
})
export class ViewProductListComponent {
  constructor(@Inject('productService') public  productService: IProductService,
              private access: AccessService,
              private router: Router) {
  }

  selectProduct(product) {
    this.productService.setSelectProduct(product);
    this.access.editPriceAndStock = true;
  }

  checkout(product) {
    this.productService.setSelectProduct(product);
    this.router.navigateByUrl('order');

  }
}
