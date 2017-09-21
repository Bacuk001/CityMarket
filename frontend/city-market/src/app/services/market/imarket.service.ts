import {Market} from '../../entity/merket';

export interface IMarketService {
  getPromiseMarkets();

  getSelectMarket();

  setSelectMarket(market: Market);

  saveMarket(market: Market);
}
