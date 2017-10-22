import {Component, Inject, Input, OnChanges, OnInit} from '@angular/core';
import {Page} from './page';
import {IProductService} from "../services/product/iproduct.service";
/**
 * Class defining the component responsible for the pigmentation of products in the application. The
 * class works with the product service interface.
 */
@Component({
    selector: 'app-pagination',
    templateUrl: './pagination.component.html',
    styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnChanges, OnInit {
    /**
     * Array of pages available for products.
     */
    public pages: Page[];
    /**
     * Stores the index of the active page from the page array.
     */
    public activPage: number;
    /**
     * Contains the possible number of displayed products on the page.
     */
    public countProductOnPage: number[];
    /**
     * The field of the object responsible for storing the value responsible for the quantity of
     * goods on the page.
     */
    public visibleProductsOnPage;
    /**
     * The property of the object is initialized by the parent class and stores the products that
     * need to be displayed on the page.
     */
    @Input() public countProduct;

    constructor(@Inject('productService') public  productService: IProductService) {
    }

    /**
     * The method is executed when the class is initialized in which an array of possible display
     * options for products on the page is initialized and a value is selected by default.
     */
    ngOnInit() {
        this.countProductOnPage = [2, 5, 10, 20];
        this.visibleProductsOnPage = this.countProductOnPage[2];
    }

    /**
     * The method is executed whenever the value of the number of products is changed by the parent
     * class. The method creates an array of pages.
     */
    ngOnChanges() {
        this.pages = new Array();
        this.activPage = 0;
        for (let index = 0; index < this.countProduct; index++) {
            let page = new Page();
            page.numberPage = index;
            page.numberOfPage = this.countProduct;
            this.pages.push(page);
        }
    }

    /**
     * The method is executed when the user selects a specific page. You can transfer the service
     * control to download products from the selected page.
     * @param numberPage
     */
    selectPage(numberPage: number) {
        this.activPage = numberPage;
        this.productService.getPromiseProducts(numberPage);
    }

    /**
     * The method changes the number of displayed products on the page.
     */
    changeCountProductOnPage() {
        this.productService.setCountProductOnPage(this.visibleProductsOnPage);
        this.productService.getPromiseProducts(0);
    }
}
