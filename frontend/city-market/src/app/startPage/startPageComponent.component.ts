import {Component, Inject, OnInit} from '@angular/core';
import {ICategoryService} from "../services/category/icategory-service.service";
import {Category} from "../entities/category";
import {Router} from "@angular/router";
import {AccessService} from "../services/access/access.service";
import {IProductService} from "../services/product/iproduct.service";
import {IMarketService} from "../services/market/imarket.service";

@Component({
    selector: 'app-startPageComponent',
    templateUrl: './startPageComponent.component.html',
    styleUrls: ['./startPageComponent.component.css']
})
export class StartPageComponent implements OnInit {
    public message: string;

    constructor(@Inject('categoryService') public categoryService: ICategoryService,
                @Inject('productService') private productService: IProductService,
                @Inject('marketService') private marketService: IMarketService,
                private router:Router ) {
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
    selectCategory(category) {
        this.categoryService.setCategory(category);
        let markets = this.marketService.getMarkets();
        this.marketService.setSelectMarket(markets[0]);
        this.router.navigateByUrl('category/listProduct').then(()=>this.message='Ошибка навигации.');
        this.productService.getPromiseProducts(0)
            .catch(error => this.message = error);
    }
}
