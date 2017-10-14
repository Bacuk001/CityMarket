import {Component, Inject, Input, OnChanges, OnInit} from '@angular/core';
import {Page} from './page';
import {IProductService} from "../services/product/iproduct.service";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnChanges {
  public pages: Page[];
  public activPage: number;
  @Input() public countProduct;

  constructor(@Inject('productService') public  productService: IProductService) {
  }

  ngOnChanges() {
    this.pages= new Array();
    this.activPage=0;
    for (let index = 0; index < this.countProduct; index++) {
      let page = new Page();
      page.numberPage = index;
      page.numberOfPage = this.countProduct;
      this.pages.push(page);
    }
  }
  selectPage(numberPage:number){
    this.activPage=numberPage;
    this.productService.getPromiseProducts(numberPage);
  }
}
