import {Component, Inject, OnInit} from '@angular/core';
import {Stock} from '../entity/stock';
import {IStockService} from '../services/stock/istock.service';

@Component({
  selector: 'app-create-stock',
  templateUrl: './create-stock.component.html',
  styleUrls: ['./create-stock.component.css']
})
export class CreateStockComponent implements OnInit {
  stock: Stock;
  message: string;

  constructor(@Inject('stockService') public stockService: IStockService) {
    this.stock = new Stock();
  }

  ngOnInit() {
  }

  createStock() {
    if (this.stock.name !== '')
      this.stockService.saveStock(this.stock)
        .then(resp => this.message = resp)
        .catch(error => this.message = error);
  }

}
