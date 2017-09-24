import {Component, Inject, OnInit} from '@angular/core';
import {IMarketService} from '../services/market/imarket.service';
import {Market} from '../entity/merket';
import {Router} from '@angular/router';
import {ICategoryService} from '../services/category/icategory-service.service';
import {AccessService} from '../services/access/access.service';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-top-navigate',
  templateUrl: './top-navigate.component.html',
  styleUrls: ['./top-navigate.component.css']
})
export class TopNavigateComponent implements OnInit {
  markets: Market[];
  market: Market;

  constructor(@Inject('marketService') private marketService: IMarketService,
              @Inject('categoryService') private categoryService: ICategoryService,
              private router: Router,
              public access: AccessService,
              private translate: TranslateService) {
  }

  ngOnInit() {
    this.marketService.getPromiseMarkets().then((resp) => {
      this.markets = resp;
    });
  }

  selectMarket() {
    this.marketService.setSelectMarket(this.market);
    this.categoryService.getPromiseCategories();
    this.router.navigateByUrl('/category');
  }

  navigate(event) {
    this.access.viewCategorySelect=true;
    this.router.navigateByUrl(event);
  }

  selectLangvich(selected){
    this.translate.use(selected);
  }
}
