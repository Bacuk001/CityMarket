import {Inject, Injectable} from '@angular/core';
import {IProductService} from './iproduct.service';
import {Http, Headers} from '@angular/http';
import {ICategoryService} from '../category/icategory-service.service';
import {IMarketService} from '../market/imarket.service';
import {Product} from '../../entities/product';
import {IAuthenticationService} from '../authentication/iauthentication.service';
import {IPriceService} from "../price/iprice.service";
import {Price} from "../../entities/price";

const URL = '/CityMarket/api/product';
const STATUS_SAVE_SUCCESS = 'Сохранено!';
const CONTENT_TYPE_VALUE = 'application/json;charset=utf-8';
const TOKEN_NAME_FIELD_HEADERS = 'X-AUTH-TOKEN';
const ERROR_LOAD = 'Ошибка загрузки!';
const NOT_SAVE = 'Не сохранено!';
const NOT_PRICE = 'Нет в наличии!';

/**
 * The class that defines the methods of interaction with products in the application.
 */
@Injectable()
export class ProductService implements IProductService {
    private sortingProduct: string;
    private products: Product[];
    private selectProduct: Product;
    private countPageProduct: number[];
    private countLoadProductOnPage: number = 10;

    constructor(private http: Http,
                @Inject('categoryService') private serviceCategory: ICategoryService,
                @Inject('marketService') private serviceMarket: IMarketService,
                @Inject('authenticationService') private authService: IAuthenticationService,
                @Inject('priceService') private priceService: IPriceService) {
    }

    /**
     * The method sends a request for all products.
     */
    getAllProducts() {
        const category = this.serviceCategory.getSelectedCategory();
        let url = URL + 's/category/' + category.id;
        return new Promise((resolve, reject) => {
            this.http.get(url)
                .subscribe(resp => resolve(resp.json())
                    , error => reject(ERROR_LOAD));
        });
    }

    /**
     * The method determines whether to load products by the warehouse or the store, forms a request
     * and sends it to receive the products. Returns Promise.
     *
     * @param {number} page
     * @returns {Promise<any>}
     */
    getPromiseProducts(page: number) {
        this.products = null;
        if (this.sortingProduct != null) return this.loadProductSortPrice(page);
        const user = this.authService.getUser();
        if (user.stock != null) {
            const category = this.serviceCategory.getSelectedCategory();
            const url = URL + 's/stock/' + user.stock.id + '/category/' + category.id;
            return this.loadProducts(url);
        }
        if (this.serviceMarket != null) {
            const market = this.serviceMarket.getSelectMarket();
            const category = this.serviceCategory.getSelectedCategory();
            const url = URL + 's/market/' + market.id + '/category/' + category.id +
                '/sizePage/' + this.countLoadProductOnPage +
                '/page/' + page;
            return this.loadProducts(url);
        }
    }

    /**
     * The method generates and sends a request to receive products.
     * @param {string} url
     * @returns {Promise<any>}
     */
    private loadProducts(url: string) {
        return new Promise((resolve, reject) => {
            this.http.get(url)
                .subscribe(resp => {
                    this.products = resp.json();
                    this.loadPrice(this.products);
                    this.downloadCountProduct();
                    return resolve(this.products);
                }, error => reject(ERROR_LOAD));
        });
    }

    /**
     * The method sends a request to receive products from the store in a certain category. Products
     * must have a fragment passed to the parameter in the product name.
     *
     * @param {string} partName
     * @returns {Promise<any>}
     */
    loadProductsByPartName(partName: string) {
        let url = URL + 's/market/' + this.serviceMarket.getSelectMarket().id + '/category/'
            + this.serviceCategory.getSelectedCategory().id + '/product/' + partName;
        const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
        return new Promise((resolve, reject) => {
            this.http.get(url, {headers: headers})
                .subscribe(resp => {
                    this.products = resp.json();
                    this.loadPrice(this.products);
                    this.countPageProduct = new Array();
                    return resolve(this.products);
                }, error => reject(ERROR_LOAD));
        });
    }

    /**
     * The method generates and sends a request to obtain the number of products.
     *
     * @returns {Promise<any>}
     */
    loadCountProduct() {
        return this.countPageProduct;
    }

    /**
     * The method counts the number of pages.
     *
     * @param {number} countProduct
     */
    counterPage(countProduct: number) {
        let countPage = Math.trunc(countProduct / this.countLoadProductOnPage);
        if (countProduct % this.countLoadProductOnPage > 0) countPage++;
        this.countPageProduct = new Array(countPage);
    }

    /**
     * The method returns a list of products stored in the service.
     *
     * @returns {Product[]}
     */
    getProducts() {
        return this.products;
    }

    /**
     * The method generates a request to save the product and sends it for storage.
     *
     * @param {Product} product
     * @returns {Promise<any>}
     */
    saveProduct(product: Product) {
        const category = this.serviceCategory.getSelectedCategory();
        const stock = this.authService.getUser().stock;
        let url = URL + '/save/category/' + category.id + '/stock/' + stock.id;
        const body = JSON.stringify(product);
        const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
        const token: string = this.authService.getToken();
        headers.append(TOKEN_NAME_FIELD_HEADERS, token);
        return new Promise((resolve, reject) => {
            this.http.post(url, body, {headers: headers})
                .subscribe(() => resolve(STATUS_SAVE_SUCCESS),
                    () => reject(NOT_SAVE));
        });
    }

    /**
     * The method returns the product that was selected for the operations.
     *
     * @returns {Product}
     */
    getSelectProduct() {
        return this.selectProduct;
    }

    /**
     * Establishes the product over which the actions will be performed.
     *
     * @param {Product} product
     */
    setSelectProduct(product: Product) {
        this.selectProduct = product;
    }

    /**
     * The method takes a list of products and loads the prices.
     *
     * @param {Product[]} products
     */
    loadPrice(products: Product[]) {
        for (let product of products) {
            this.priceService.getPriceByProduct(product)
                .then(resp => this.selectionOfPrice(product, resp))
        }
    }

    /**
     * The method determines the first price that is in the warehouses and assigns a value to the
     * field that is displayed to the buyer.
     */
    selectionOfPrice(product: Product, prices: Price[]) {
        product.price = prices;
        for (let price of prices) {
            if (price.inStock > 0) {
                product.priceView = price.price.toString();
                return;
            }
        }
        product.priceView = NOT_PRICE;
    }

    /**
     * Method requests from the server the quantity of goods in the warehouse.
     */
    private downloadCountProduct() {
        const market = this.serviceMarket.getSelectMarket();
        const category = this.serviceCategory.getSelectedCategory();
        let url = URL + 's/market/' + market.id + '/category/' + category.id + '/count';
        const headers = new Headers({'Content-Type': CONTENT_TYPE_VALUE});
        this.http.get(url, {headers: headers})
            .subscribe(resp => this.counterPage(resp.json()));
    }

    /**
     * The method sets the number of products that will be loaded to display on a single page.
     *
     * @param {number} countProduct
     */
    setCountProductOnPage(countProduct: number) {
        this.countLoadProductOnPage = countProduct;
    }

    /**
     * The method refers to the service to obtain products sorted by price.
     */
    private loadProductSortPrice(page: number) {
        this.priceService.getCountProductCategoryAndMarket().then(resp => this.counterPage(resp));
        return this.priceService
            .getPriceByCategoryAndMarketWithProduct(this.sortingProduct, page, this.countLoadProductOnPage)
            .then(resp => this.products = resp);
    }

    /**
     * The method takes a string that specifies the sorting direction. Possible options desks and
     * abs. If the value is then loaded, the products will not be sorted.
     *
     * @param sortingProduct
     */
    setSortingProduct(sortingProduct: string) {
        this.sortingProduct = sortingProduct;
    }
}
