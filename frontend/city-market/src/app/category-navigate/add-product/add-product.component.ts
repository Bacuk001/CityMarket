import {Component, Inject, OnInit} from '@angular/core';
import {Product} from '../../entity/product';
import {IAuthenticationService} from '../../services/authentication/iauthentication.service';
import {IProductService} from '../../services/product/iproduct.service';
import {ICategoryService} from '../../services/category/icategory-service.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  public product: Product;

  constructor(@Inject('authenticationService') private authenticationService: IAuthenticationService,
              @Inject('productService') private productService: IProductService,
              @Inject('categoryService') private categoryService: ICategoryService) {
    this.product = new Product();
  }

  ngOnInit() {
  }

  saveProduct() {
    this.product.stock = this.authenticationService.getUser().stock;
    this.product.category = this.categoryService.getSelectedCategory();
    this.productService.saveProduct(this.product);
  }
}
