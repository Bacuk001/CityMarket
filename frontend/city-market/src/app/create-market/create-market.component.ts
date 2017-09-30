import {Component, Inject, OnInit} from '@angular/core';
import {IMarketService} from '../services/market/imarket.service';
import {Router} from '@angular/router';
import {Market} from '../entities/merket';

/**
 * Component for creating a market.
 */
@Component({
  selector: 'app-create-market',
  templateUrl: './create-market.component.html',
  styleUrls: ['./create-market.component.css']
})
export class CreateMarketComponent implements OnInit {
  /**
   * List of all created markets.
   */
  public markets: Market[];
  /**
   * Market that will be registered in the system.
   */
  public market: Market;
  /**
   * Message that will be sent to the user during the use of the components.
   */
  public message: string;

  constructor(@Inject('marketService') public marketService: IMarketService) {
  }

  /**
   * At initialization, a new instance of the market is created.
   */
  ngOnInit() {
    this.market = new Market();
    this.marketService.getPromiseMarkets()
      .then(resp => this.markets = resp)
      .catch(error => this.message = error);
  }

  /**
   * The market is transferred to the service for storage.
   */
  createMarket() {
    if (this.market.name !== '')
      this.marketService.saveMarket(this.market)
        .then(resp => this.message = resp)
        .cacth(error => this.message = error);
    this.marketService.getPromiseMarkets();
  }
  claerEditMarket(){
    this.market = new Market();
  }
}
