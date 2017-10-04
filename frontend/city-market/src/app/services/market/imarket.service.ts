import {Market} from '../../entities/merket';

/**
 * The interface describes methods for working with markets.
 */
export interface IMarketService {
  /**
   * The method generates a query to obtain a list of markets created in the application.
   */
  getMarkets();

  /**
   * The method returns the store that was selected for work.
   */
  getSelectMarket();

  /**
   * The method establishes a store with which the products will be taken off.
   */
  setSelectMarket(market: Market);

  /**
   * The method generates a request to store the store.
   */
  saveMarket(market: Market);

  /**
   * The method sends a request to the server to get all the markets registered in the system.
   */
  loadMarkets();
  setToken(token:string);
}
