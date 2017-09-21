import {Component, Inject, OnInit} from '@angular/core';
import {IAuthenticationService} from '../services/authentication/iauthentication.service';
import {IStockService} from '../services/stock/istock.service';
import {IMarketService} from '../services/market/imarket.service';
import {Stock} from '../entity/stock';
import {Market} from '../entity/merket';
import {Role} from '../entity/role';
import {User} from '../entity/user';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  stocks: Stock[];
  markets: Market[];
  roles: Role[];
  message: string;
  checkedRole: boolean[];
  user: User;

  constructor(@Inject('authenticationService') private authService: IAuthenticationService,
              @Inject('stockService') public stockService: IStockService,
              @Inject('marketService') public marketService: IMarketService) {

  }

  ngOnInit() {
    this.user = new User();
    this.marketService.getPromiseMarkets()
      .then(resp => this.markets = resp)
      .catch(error => this.message = error);
    this.stockService.getStocksToPromise()
      .then(resp => this.stocks = resp)
      .catch(error => this.message = error);
    this.authService.getRolesToPromise()
      .then(resp => {
        this.roles = resp;
        this.checkedRole = new Array(this.roles.length);
      })
      .catch(error => this.message = error);
  }

  createUser() {
    this.user.roles = new Array();
    for (let index = 0; index < this.roles.length; index++)
      if (this.checkedRole[index]) this.user.roles.push(this.roles[index]);
    this.authService.saveUser(this.user)
      .then(resp => this.message = resp)
      .catch(error => this.message = error);

  }
}
