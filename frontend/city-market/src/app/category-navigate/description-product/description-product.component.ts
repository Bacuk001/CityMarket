import {Component, Inject, OnInit} from '@angular/core';
import {IProductService} from "../../services/product/iproduct.service";
import {IDescriptionService} from "../../services/description/idescription.service";
import {Router} from "@angular/router";
import {Product} from "../../entity/product";
import {Description} from "../../entity/description";

@Component({
  selector: 'app-description-product',
  templateUrl: './description-product.component.html',
  styleUrls: ['./description-product.component.css']
})
export class DescriptionProductComponent implements OnInit {
  public product: Product;
  public descriptions: Description[];
  public message: string;

  constructor(@Inject('productService') private productService: IProductService,
              @Inject('descriptionService') private descriptionService: IDescriptionService,
              router: Router) {
  }

  ngOnInit() {
    this.product = this.productService.getSelectProduct();
    this.descriptionService.getDescriptionByProduct(this.product)
      .then(resp => this.descriptions = resp)
      .catch(error => this.message = error);
  }


}
