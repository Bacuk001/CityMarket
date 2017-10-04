import {Component, Inject, OnInit} from '@angular/core';
import {IProductService} from "../../services/product/iproduct.service";
import {IDescriptionService} from "../../services/description/idescription.service";
import {Router} from "@angular/router";
import {Product} from "../../entities/product";
import {Description} from "../../entities/description";

/**
 * Component that displays a list of descriptions of the product.
 */
@Component({
  selector: 'app-description-product',
  templateUrl: './description-product.component.html',
  styleUrls: ['./description-product.component.css']
})
export class DescriptionProductComponent implements OnInit {
  /**
   * Product characteristics you want to display.
   */
  public product: Product;
  /**
   * List of characteristics for the product.
   */
  public descriptions: Description[];
  /**
   * Contains messages sent to the user about the progress of the program.
   */
  public message: string;

  constructor(@Inject('productService') private productService: IProductService,
              @Inject('descriptionService') private descriptionService: IDescriptionService,
              public router: Router) {
  }

  /**
   * when the product is initialized, the selected product from the service is initialized.
   */
  ngOnInit() {
    this.product = this.productService.getSelectProduct();
    this.descriptionService.getDescriptionsByProduct(this.product)
      .then(resp => this.descriptions = resp)
      .catch(error => this.message = error);
  }
}
