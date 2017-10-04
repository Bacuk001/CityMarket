import {Component, Inject, OnInit} from '@angular/core';
import {IAuthenticationService} from '../services/authentication/iauthentication.service';
import {IStockService} from '../services/stock/istock.service';
import {IMarketService} from '../services/market/imarket.service';
import {Stock} from '../entities/stock';
import {Market} from '../entities/merket';
import {Role} from '../entities/role';
import {User} from '../entities/user';

/**
 * Component that allows you to create users of the system.
 */
@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {
  /**
   * Users registered in the system.
   */
  public users: User[];
  /**
   * List of warehouses that are registered in the system.
   */
  public stocks: Stock[];
  /**
   * List of markets that are registered in the system.
   */
  public markets: Market[];
  /**
   * List of roles that are registered in the system.
   */
  public roles: Role[];
  /**
   * Messages that will be transmitted to the user in the process of using the component.
   */
  public message: string;
  /**
   * List of selected roles.
   */
  public checkedRole: boolean[];
  /**
   * Created user for registration in the system.
   */
  public user: User;

  constructor(@Inject('authenticationService') private authService: IAuthenticationService,
              @Inject('stockService') public stockService: IStockService,
              @Inject('marketService') public marketService: IMarketService) {

  }

  /**
   * At initialization all necessary data are loaded for creation of the user. Warehouses, shops
   * and roles.
   */
  ngOnInit() {
    this.user = new User();
    this.loadMarkets();
    this.loadStock();
    this.loadUser()
    this.loadRole()
  }

  /**
   * The method loads all markets registered in the system.
   */
  private loadMarkets() {
    this.marketService.loadMarkets()
      .then(resp => this.markets = resp)
      .catch(error => this.message = error);
  }

  /**
   * The method loads all stocks registered in the system.
   */
  private loadStock() {
    this.stockService.getStocksToPromise()
      .then(resp => this.stocks = resp)
      .catch(error => this.message = error);
  }

  /**
   * The method loads all users registered in the system.
   */
  private loadUser() {
    this.authService.getUsers()
      .then(resp => this.users = resp)
      .catch(error => this.message = error);
  }

  /**
   * Loads the password for the user.
   *
   * @param {User} user
   */
  public loadPassword(user: User) {
    this.user = user;
    this.authService.getUserPassword(user.id)
      .then(resp => this.user.password = resp)
      .catch(error => this.message = error);
  }

  /**
   * The method loads all roles registered in the system.
   */
  private loadRole() {
    this.authService.getRolesToPromise()
      .then(resp => {
        this.roles = resp;
        this.checkedRole = new Array(this.roles.length);
      })
      .catch(error => this.message = error);
  }

  /**
   * The method passes to the user service for registration in the system.
   */
  createUser() {
    this.user.roles = new Array(1);
    for (let index = 0; index < this.roles.length; index++)
      if (this.checkedRole[index]) this.user.roles.push(this.roles[index]);
    this.authService.saveUser(this.user)
      .then(resp => {
        this.message = resp;
        setTimeout(() => this.message = '', 1000);
      })
      .catch(error => {
        this.message = error;
        setTimeout(() => this.message = '', 1000);
      });
  }

  /**
   * clear fields for creating and editing a user.
   */
  clearFields() {
    this.user = new User();
  }
}
