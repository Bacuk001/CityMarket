import {Component} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {AccessService} from "./services/access/access.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor(private translate: TranslateService, public access:AccessService) {
    translate.use('ru');
  }
}
