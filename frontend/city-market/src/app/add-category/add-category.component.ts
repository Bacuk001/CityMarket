import {Component, Inject, OnInit} from '@angular/core';
import {Category} from '../entity/category';
import {ICategoryService} from '../services/category/icategory-service.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {
  message: string;
  category: Category;

  constructor(@Inject('categoryService') private categoryService: ICategoryService) {
    this.category = new Category();
  }

  ngOnInit() {
  }

  save() {
    this.categoryService.saveCategory(this.category).subscribe((resp: Response) => {
      this.message = 'Данные успешно добавлены';
    });
  }
}
