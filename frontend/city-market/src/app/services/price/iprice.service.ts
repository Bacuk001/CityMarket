import {Product} from '../../entities/product';
import {Price} from '../../entities/price';

/**
 *  Interface describing the basic methods for working with the prices of products.
 */
export interface IPriceService {
    /**
     * The method determines for which the rest should be loaded. The remains of a warehouse or
     *  shop. Generates a request for data and requests a return promise.
     */
    getPriceByProduct(product: Product)

    /**
     *The method generates a query to store the price of the product in the database.
     * @param {Price} price
     */
    savePrice(price: Price);
    /**
     * The method is accessed through the URL to obtain a list of prices with the products in
     * sorted form and passes them on to form a list of products. The method returns an
     * estimate.
     * @returns {Promise<T>}
     */
    getPriceByCategoryAndMarketWithProduct(directionSorting: string, pageNumber: number, sizePage: number);
    getCountProductCategoryAndMarket();

}
