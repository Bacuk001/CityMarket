import {Category} from '../../entity/category';

export interface ICategoryService {
  getPromiseCategories();

  getCategories();

  getSelectedCategory();

  setCategory(category: Category);

  saveCategory(category: Category);

  deleteCategory(category: Category);
}

