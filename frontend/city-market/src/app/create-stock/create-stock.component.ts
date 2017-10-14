import {Component, Inject, OnInit} from '@angular/core';
import {Stock} from '../entities/stock';
import {IStockService} from '../services/stock/istock.service';

/**
 * Component for creating a stock.
 */
@Component({
  selector: 'app-create-stock',
  templateUrl: './create-stock.component.html',
  styleUrls: ['./create-stock.component.css']
})
export class CreateStockComponent implements OnInit {
  /**
   * List of warehouses in the system.
   */
  public stocks: Stock[];
  /**
   * The warehouse to be created.
   */
  public stock: Stock;
  /**
   * Messages that will be sent to the application user.
   */
  public message: string;
  /**
   * Determines the button's click.
   */
  public isDisableSaveButton: boolean = false;

  constructor(@Inject('stockService') public stockService: IStockService) {
    this.stock = new Stock();
  }

  ngOnInit() {
    this.stockService.getStocksToPromise()
      .then(resp => this.stocks = resp)
      .catch(error => this.message = error)
  }

  /**
   * The method transfers the warehouse to the service for registration, storing the stock.
   */
  createStock() {
    this.isDisableSaveButton = true;
    if (this.stock.name !== '')
      this.stockService.saveStock(this.stock)
        .then(resp => this.afterSuccessfullySaving(resp))
        .catch(error => this.message = error);
  }

  /**
   * Cancel editing and clearing fields.
   */
  clearFormInputs() {
    this.stock = new Stock();
  }

  /**
   * The method is executed when the warehouse is successfully saved.
   */
  private afterSuccessfullySaving(response) {
    this.isDisableSaveButton = false;
    this.message = response;
    this.ngOnInit();
    this.clearFormInputs();
    setTimeout(() => this.message = '', 1000);
  }
}
