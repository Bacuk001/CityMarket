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
    if (this.stock.name !== '')
      this.stockService.saveStock(this.stock)
        .then(resp => this.message = resp)
        .catch(error => this.message = error);
  }

  /**
   * Cancel editing and clearing fields.
   */
  clearStock() {
    this.stock = new Stock();
  }

}
