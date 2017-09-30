import {Component, Inject, OnInit} from '@angular/core';
import {IMarketService} from '../services/market/imarket.service';
import {Market} from '../entities/merket';
import {Router} from '@angular/router';
import {ICategoryService} from '../services/category/icategory-service.service';
import {AccessService} from '../services/access/access.service';
import {TranslateService} from "@ngx-translate/core";

/**
 * The component at the top of the page is designed to contain navigation navigation, the choice of
 * the store and the language of the application.
 */
@Component({
  selector: 'app-top-navigate',
  templateUrl: './top-navigate.component.html',
  styleUrls: ['./top-navigate.component.css']
})
export class TopNavigateComponent implements OnInit {
  /**
   * List of stores registered in the system for selection.
   */
  public markets: Market[];
  /**
   * Market that has been selected.
   */
  public market: Market;


  constructor(@Inject('marketService') private marketService: IMarketService,
              @Inject('categoryService') private categoryService: ICategoryService,
              private router: Router,
              public access: AccessService,
              private translate: TranslateService) {
  }

  /**
   * When the components are initialized, all markets are loaded.
   */
  ngOnInit() {
    this.marketService.getPromiseMarkets()
      .then((resp) => {
        this.markets = resp;
        this.selectDefaultMarket(this.markets[0])
      });
  }

  /**
   * The method is executed when the store is selected. access to product categories is opened, the
   * selected store is registered in the service and product categories are loaded.
   */
  selectMarket() {
    this.access.viewCategorySelect = true;
    this.marketService.setSelectMarket(this.market);
    this.categoryService.getPromiseCategories();
    this.router.navigateByUrl('/category').then();
  }

  /**
   * Method of guiding the route transmitted in the parameters.
   *
   * @param event
   */
  navigate(event) {
    this.router.navigateByUrl(event).then();
  }

  /**
   * A method that changes the language in the application.
   * @param selected
   */
  selectLanguage(selected) {
    this.translate.use(selected);
  }

  /**
   * Market that is initialized by default.
   *
   * @param {Market} market
   */
  selectDefaultMarket(market: Market) {
    this.marketService.setSelectMarket(market);
  }
}
