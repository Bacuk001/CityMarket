import {Component, Inject, OnInit} from '@angular/core';
import {IMarketService} from '../services/market/imarket.service';
import {Router} from '@angular/router';
import {Market} from '../entity/merket';

@Component({
  selector: 'app-create-market',
  templateUrl: './create-market.component.html',
  styleUrls: ['./create-market.component.css']
})
export class CreateMarketComponent implements OnInit {
  market: Market;
  message: string;

  constructor(@Inject('marketService') public marcetService: IMarketService,
              private router: Router) {

  }

  ngOnInit() {
    this.market = new Market();
  }

  createMarket() {
    if (this.market.name !== '')
      this.marcetService.saveMarket(this.market)
        .then(resp => this.message = resp)
        .cacth(error => this.message = error);
    this.marcetService.getPromiseMarkets();
  }
}
