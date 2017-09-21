import {Component, Inject, OnInit} from '@angular/core';
import {IStockService} from '../services/stock/istock.service';
import {Stock} from '../entity/stock';
import {IAuthenticationService} from '../services/authentication/iauthentication.service';
import {Market} from '../entity/merket';

@Component({
  selector: 'app-sign-stock-market',
  templateUrl: './sign-stock-market.component.html',
  styleUrls: ['./sign-stock-market.component.css']
})
export class SignStockMarketComponent implements OnInit {
  public check: boolean[];
  public stocks: Stock[];
  public stocksMarketSign: Stock[];
  public market: Market;
  public message: string;

  constructor(@Inject('stockService') public stockService: IStockService,
              @Inject('authenticationService') private authService: IAuthenticationService) {

  }

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

  sendSign() {
    this.findCheckStock();
    this.stockService.signStockMarket(this.market, this.stocksMarketSign);
  }

  findCheckStock() {
    this.stocksMarketSign = new Array();
    for (let index = 0; index < this.check.length; index++) {
      if (this.check[index]) this.stocksMarketSign.push(this.stocks[index]);
    }
  }

  updateStockMarket(stock: Stock) {
    for (let index = 0; index < this.stocksMarketSign.length; index++) {
      if (this.stocksMarketSign[index].id = stock.id) return;
    }
    stock.id = null;
    this.stocksMarketSign[this.stocksMarketSign.length] = stock;
  }
}
