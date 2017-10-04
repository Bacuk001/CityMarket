import {Stock} from '../../entities/stock';
import {Market} from '../../entities/merket';

/**
 * Interface for working with product storage.
 */
export interface IStockService {
  /**
   * The method saves the created store.
   *
   * @param {Stock} stock
   */
  saveStock(stock: Stock);

  /**
   *  The method creates a query in the database to obtain a list of warehouses.
   */
  getStocksToPromise();

  /**
   * The method returns the stock registered for the actions.
   */
  getSelectedStock();

  /**
   * The method establishes a warehouse for the work to be carried out.
   *
   * @param {Stock} stock
   */
  setSelectedStock(stock: Stock);

  /**
   * The method forms a query in the database to obtain all the warehouses to which the market is
   * subscribed.
   *
   * @param {Market} market
   */
  getStocksByMarket(market: Market);

  /**
   * The method signs the store on the storage list.
   *
   * @param {Market} market
   * @param {Stock[]} stock
   */
  signStockMarket(market: Market, stock: Stock[]);

}
