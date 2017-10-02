import {Component, Inject, OnInit} from '@angular/core';
import {ICategoryService} from '../services/category/icategory-service.service';
import {Category} from '../entities/category';
import {Router} from '@angular/router';
import {IProductService} from '../services/product/iproduct.service';
import {IAuthenticationService} from '../services/authentication/iauthentication.service';
import {AccessService} from '../services/access/access.service';
import {Product} from "../entities/product";
import {IPriceService} from "../services/price/iprice.service";

/**
 * Component that displays categories and products over which actions are performed.
 */
@Component({
  selector: 'category-navigate',
  templateUrl: './category-navigate.component.html',
  styleUrls: ['./category-navigate.component.css']
})
export class CategoryNavigateComponent implements OnInit {
  /**
   * Messages for the user during the application process.
   */
  public message: string;

  constructor(@Inject('authenticationService') public authService: IAuthenticationService,
              @Inject('categoryService') public categoryService: ICategoryService,
              @Inject('productService') private productService: IProductService,
              @Inject('priceService') private priceService: IPriceService,
              public accessService: AccessService,
              private router: Router) {
  }

  /**
   * When initializing, we instruct the service to load product categories.
   */
  ngOnInit() {
    this.categoryService.getPromiseCategories();
  }

  /**
   * The method is executed when a category is selected. the first page of products in the category
   * is loaded. If error, then a message is displayed.
   *
   * @param {Category} category
   */
  selectCategory(category: Category) {
    this.accessService.editPriceAndStock = false;
    this.categoryService.setCategory(category);
    this.productService.getPromiseProducts(0)
      .catch(error => this.message = error);
    this.router.navigateByUrl('/category/listProduct')
      .then().catch((error => this.message = error))
  }

  /**
   * The method is executed when you press the navigation buttons. The buttons have routes. and the
   * routes are passed to the method.
   *
   * @param rout
   */
  navigate(rout) {
    this.router.navigateByUrl(rout)
      .then().catch((error => this.message = 'Error load url!'));
  }
}
