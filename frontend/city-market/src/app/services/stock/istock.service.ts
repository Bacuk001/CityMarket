import {Stock} from '../../entity/stock';
import {Market} from '../../entity/merket';

export interface IStockService {
  saveStock(stock: Stock);

  getStocksToPromise();

  getSelectedStock();

  setSelectedStock(stock: Stock);

  getStocksByMarket(market: Market);

  signStockMarket(market: Market, stock: Stock[]);

}
