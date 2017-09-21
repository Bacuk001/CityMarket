import {Component, Inject, OnInit} from '@angular/core';
import {IAuthenticationService} from '../services/authentication/iauthentication.service';
import {User} from '../entity/user';


@Component({
  selector: 'app-rigth-navigate',
  templateUrl: './rigth-navigate.component.html',
  styleUrls: ['./rigth-navigate.component.css']
})
export class RigthNavigateComponent implements OnInit {
  user: User;

  constructor(@Inject('authenticationService') public authService: IAuthenticationService) {
  }

  ngOnInit() {
  }

  loginSystem() {
    this.authService.loginSystem().then(resp => {
      this.user = resp;
    });
  }

  logOut() {
    this.authService.logOut();
  }
}
