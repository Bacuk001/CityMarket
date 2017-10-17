import {Component, Inject, OnInit} from '@angular/core';
import {IMarketService} from "../services/market/imarket.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(@Inject('marketService') public marketService: IMarketService) {
  }

  ngOnInit() {
  }
}
