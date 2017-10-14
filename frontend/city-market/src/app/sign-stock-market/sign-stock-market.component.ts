import {Component, Inject, OnInit} from '@angular/core';
import {IStockService} from '../services/stock/istock.service';
import {Stock} from '../entities/stock';
import {IAuthenticationService} from '../services/authentication/iauthentication.service';
import {Market} from '../entities/merket';

/**
 * The component is available only to the store administrator, carries out the store's signature on
 * the warehouses. The store can take goods and prices from those warehouses for which it is signed.
 */
@Component({
  selector: 'app-sign-stock-market',
  templateUrl: './sign-stock-market.component.html',
  styleUrls: ['./sign-stock-market.component.css']
})
export class SignStockMarketComponent implements OnInit {
  /**
   * All items that are installed as correctly will be signed to the store, the rest are deleted.
   */
  public check: boolean[];
  /**
   * List of warehouses registered in the system.
   */
  public stocks: Stock[];
  /**
   * The list of warehouses for which the store is subscribed.
   */
  public stocksMarketSign: Stock[];
  /**
   * Store that will be signed.
   */
  public market: Market;
  /**
   * messages to the user during the execution of the application.
   */
  public message: string;

  constructor(@Inject('stockService') public stockService: IStockService,
              @Inject('authenticationService') private authService: IAuthenticationService) {
  }

  /**
   * when the component is initialized, the user is removed from the registration service and all
   * the warehouses to which the store is subscribed and all registered are questioned from the
   * service.
   */
  ngOnInit() {
    this.market = this.authService.getUser().market;
    this.stockService.getStocksToPromise()
      .then(res => {
        this.stocks = res;
        this.check = new Array(this.stocks.length);
      })
      .catch(error => this.message = error);
    this.stockService.getStocksByMarket(this.market)
      .then(resp => this.stocksMarketSign = resp)
      .catch(error => this.message = error);
  }

  /**
   * The method sends to the service store and a list of warehouses, for signing and sending to the
   * database.
   */
  sendSign() {
    this.findCheckStock();
    this.stockService.signStockMarket(this.market, this.stocksMarketSign);
  }

  /**
   * The method determines which of the warehouses have been selected.
   */
  findCheckStock() {
    this.stocksMarketSign = new Array();
    for (let index = 0; index < this.check.length; index++) {
      if (this.check[index]) this.stocksMarketSign.push(this.stocks[index]);
    }
  }
}
