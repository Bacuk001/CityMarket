import {Component, Inject, OnInit} from '@angular/core';
import {IDescriptionService} from "../../services/description/idescription.service";
import {IProductService} from "../../services/product/iproduct.service";
import {Product} from "../../entity/product";
import {Description} from "../../entity/description";

@Component({
  selector: 'app-description-product-create',
  templateUrl: './description-product-create.component.html',
  styleUrls: ['./description-product-create.component.css']
})
export class DescriptionProductCreateComponent implements OnInit {
  public product: Product;
  public descriptions: Description[];
  public descriptionCreate : Description;
  public message: string;

  constructor(@Inject('descriptionService') private descriptionService: IDescriptionService,
              @Inject('productService') private productService: IProductService) {
  }

  ngOnInit() {
    this.descriptionCreate= new Description();
    this.product = this.productService.getSelectProduct();
    this.descriptionService.getDescriptionByProduct(this.product)
      .then(resp => this.descriptions = resp)
      .catch(error => this.message = error);
  }

  addDescription() {
    this.descriptions.push(this.descriptionCreate);
    this.descriptionCreate =  new Description();
  }

  saveDescriptions() {
    for (let description of this.descriptions) description.product = this.product
    this.descriptionService.saveDescription(this.descriptions)
      .then(resp => this.message = resp)
      .catch(error => this.message = error);
  }
}
