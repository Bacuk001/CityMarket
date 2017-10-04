import {Component, Inject, OnInit} from '@angular/core';
import {ICategoryService} from "../services/category/icategory-service.service";
import {Category} from "../entities/category";
import {Router} from "@angular/router";
import {AccessService} from "../services/access/access.service";
import {IProductService} from "../services/product/iproduct.service";

@Component({
  selector: 'app-startPageComponent',
  templateUrl: './startPageComponent.component.html',
  styleUrls: ['./startPageComponent.component.css']
})
export class StartPageComponent implements OnInit {
  public message:string;

  constructor(@Inject('categoryService') public categoryService: ICategoryService,
              private router: Router,
              private accessService: AccessService,
              @Inject('productService') private productService: IProductService) {
  }

  ngOnInit() {
    this.categoryService.getPromiseCategories();
  }

  /**
   * The method is executed when a category is selected. the first page of products in the category
   * is loaded. If error, then a message is displayed.
   *
   * @param {Category} category
   */
  selectCategory() {
    alert('Для просмотра необходимо выбрать магазин!');
  }
}
