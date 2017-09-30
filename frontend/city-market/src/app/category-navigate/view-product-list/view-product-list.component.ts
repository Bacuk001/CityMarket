import {
  AfterContentChecked, AfterContentInit, AfterViewInit, Component, Inject, OnChanges,
  OnInit
} from '@angular/core';
import {IProductService} from '../../services/product/iproduct.service';
import {AccessService} from '../../services/access/access.service';
import {Router} from '@angular/router';
import {IPriceService} from "../../services/price/iprice.service";
import {Product} from "../../entities/product";
import {Http} from "@angular/http";
import {IMarketService} from "../../services/market/imarket.service";
import {Price} from "../../entities/price";

const NOT_AVARIBLE = 'Нет в наличии'

@Component({
  selector: 'app-view-product-list',
  templateUrl: './view-product-list.component.html',
  styleUrls: ['./view-product-list.component.css']
})
export class ViewProductListComponent implements OnInit {
  /**
   * Messages for the user during the application process.
   */
  public message: string;
  /**
   * Stores the number of pages in the category.
   * @type {any[]}
   */
  public pageCount: number[] = new Array(1);

  constructor(@Inject('productService') public  productService: IProductService,
              @Inject('priceService') public priceService: IPriceService,
              @Inject('marketService') public marketService: IMarketService,
              private access: AccessService,
              private router: Router) {
  }

  /**
   * If the market is selected, the method requests the quantity of the product in the category
   * from the service and calculates the number of pages. The method is executed when the component
   * is initialized.
   */
  ngOnInit() {
    if (this.marketService.getSelectMarket() != null)
      this.productService.loadCountProduct().then(resp => {
        let countPages = Math.trunc(resp / 10);
        if (resp % 10 > 0) countPages++;
        this.pageCount = new Array(countPages);
      });
  }

  /**
   * Registers the selected product in the service.
   *
   * @param product
   */
  selectProduct(product) {
    this.productService.setSelectProduct(product);
    this.access.editPriceAndStock = true;
  }

  /**
   * The method initializes the selected product in the service and redirects it to the component
   * of the order.
   *
   * @param product
   */
  checkout(product) {
    this.productService.setSelectProduct(product);
    this.router.navigateByUrl('order');
  }

  /**
   * The method initializes the selected product in the service and the component to view the
   * product description.
   *
   * @param product
   */
  viewDescription(product) {
    this.productService.setSelectProduct(product);
    this.router.navigateByUrl('category/viewDescription').catch();
  }

  /**
   * The method takes the page number and loads the products from the page.
   *
   * @param {number} page
   */
  selectPage(page: number) {
    this.productService.getPromiseProducts(page)
      .then().catch(error => this.message = error);
  }
}
