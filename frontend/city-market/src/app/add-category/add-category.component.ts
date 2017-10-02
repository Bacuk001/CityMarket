import {Component, Inject, OnInit} from '@angular/core';
import {Category} from '../entities/category';
import {ICategoryService} from '../services/category/icategory-service.service';

/**
 * Component for creating a category.
 */
@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {
  /**
   * Categories registered in the application.
   */
  public categories: Category[]
  /**
   * Which will be displayed to the user during the program's execution.
   */
  public message: string;
  /**
   * A category that, after filling in the fields, will be stored in the system.
   */
  public category: Category;

  constructor(@Inject('categoryService') private categoryService: ICategoryService) {
    this.category = new Category();
  }

  /**
   * The method is executed when the component is initialized. The method accesses the service to
   * retrieve all product groups and stores them in the component.
   */
  ngOnInit() {
    this.categoryService.getPromiseCategories()
      .then(resp => this.categories = resp)
      .catch(error => this.message = error);
  }

  /**
   * The method passes the category to the service for saving.
   */
  save() {
    this.categoryService.saveCategory(this.category)
      .subscribe(resp => this.message = "Saved!",
        error => this.message = error);
  };

  clear() {
    this.category = new Category;
  }

}
