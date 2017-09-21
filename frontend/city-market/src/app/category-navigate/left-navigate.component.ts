import {Component, Inject, OnInit} from '@angular/core';
import {ICategoryService} from '../services/category/icategory-service.service';
import {Category} from '../entity/category';
import {Router} from '@angular/router';
import {IProductService} from '../services/product/iproduct.service';
import {IAuthenticationService} from '../services/authentication/iauthentication.service';
import {AccessService} from '../services/access/access.service';

@Component({
  selector: 'app-left-navigate',
  templateUrl: './left-navigate.component.html',
  styleUrls: ['./left-navigate.component.css']
})
export class LeftNavigateComponent implements OnInit {

  constructor(@Inject('categoryService') public categoryService: ICategoryService,
              private router: Router,
              @Inject('productService') private productService: IProductService,
              @Inject('authenticationService') public authService: IAuthenticationService,
              public accessService: AccessService) {
  }

  ngOnInit() {
    this.categoryService.getPromiseCategories();
  }

  selectCategory(category: Category) {
    this.accessService.editPriceAndStock = false;
    this.categoryService.setCategory(category);
    this.productService.getPromiseProducts()
      .then(() => this.productService.loadPriseForProducts());
    this.router.navigateByUrl('/category/listProduct');
  }

  navigate(event) {
    this.router.navigateByUrl(event);
  }
}
