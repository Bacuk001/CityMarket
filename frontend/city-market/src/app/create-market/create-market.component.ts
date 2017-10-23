import {Component, Inject, OnInit} from '@angular/core';
import {IMarketService} from '../services/market/imarket.service';
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
  /**
   * Determines the button's click.
   */
  public isDisableSaveButton: boolean = false;

  constructor(@Inject('marketService') public marketService: IMarketService) {
  }

  /**
   * At initialization, a new instance of the market is created.
   */
  ngOnInit() {
    this.market = new Market();
    this.marketService.loadMarkets()
      .then(resp => this.markets = resp)
      .catch(error => this.message = error);
  }

  /**
   * The market is transferred to the service for storage.
   */
  createMarket() {
    this.isDisableSaveButton = true;
    if (this.market.name !== '')
      this.marketService.saveMarket(this.market)
        .then(resp => {
          this.isDisableSaveButton = false;
          this.message = resp;
          this.ngOnInit();
          setTimeout(() => this.message = '', 1000);
          this.clearFormInput();
        }).catch(error => {
        this.isDisableSaveButton = false;
        this.message = error;
        setTimeout(() => this.message = '', 1000);
      });
  }

  /**
   * Clear form fields.
   */
  clearFormInput() {
    this.market = new Market();
  }
}
