import {Component, Inject} from "@angular/core";
import {IProductService} from "../../services/product/iproduct.service";
import {AccessService} from "../../services/access/access.service";
import {Router} from "@angular/router";
import {IPriceService} from "../../services/price/iprice.service";
import {IMarketService} from "../../services/market/imarket.service";
import {ICategoryService} from "../../services/category/icategory-service.service";
import {Product} from "../../entities/product";

@Component({
    selector: 'app-view-product-list',
    templateUrl: './view-product-list.component.html',
    styleUrls: ['./view-product-list.component.css']
})
export class ViewProductListComponent {
    /**
     * Messages for the user during the application process.
     */
    public message: string;
    public partName: string;

    constructor(@Inject('productService') public  productService: IProductService,
                @Inject('priceService') public priceService: IPriceService,
                @Inject('marketService') public marketService: IMarketService,
                @Inject('categoryService') public categoryService: ICategoryService,
                public access: AccessService,
                private router: Router) {
    }

    /**
     * Registers the selected product in the service.
     *
     * @param product
     */
    selectProduct(product) {
        this.productService.setSelectProduct(product);
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
     * The method registers the selected product in the service and redirects it to work with the
     * product. Depending on the chosen route, the characteristics or changes in price and
     * balance will be corrected.
     * @param navigate
     * @param product
     */
    handlerProduct(navigate: string, product: Product) {
        this.productService.setSelectProduct(product);
        this.router.navigateByUrl(navigate).then(() => this.message = 'Ошибка навигации.');
    }

    sortProduct(direction: string) {
        this.productService.setSortingProduct(direction);
        this.productService.getPromiseProducts(0);
    }

    sortProductByPrice(sortDown: boolean) {
        if (sortDown) this.productService.getProducts().sort(this.sortDown);
        if (!sortDown) this.productService.getProducts().sort(this.sortUp);
    }

    sortDown(product1: Product, product: Product) {
        if (product1.priceView > product.priceView) {
            return 1;
        }
        if (product1.priceView < product.priceView) {
            return -1;
        }
        return 0;
    }

    sortUp(product1: Product, product: Product) {
        if (product1.priceView > product.priceView) {
            return -1;
        }
        if (product1.priceView < product.priceView) {
            return 1;
        }
        return 0;
    }

    /**
     * The method is called when the input field of the product name is changed. The method passes a
     * string to the service that should be contained in the product name. And instructs the service
     * about the need to download products in the name of which this line is contained. In the case
     * of an empty string, the method indicates the download service for all products.
     */
    changeListProductByPartName() {
        if (this.partName != "") this.productService.loadProductsByPartName(this.partName);
        if (this.partName == "") this.productService.getPromiseProducts(0);
    }
}
