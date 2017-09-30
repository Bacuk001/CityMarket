import {Component, Inject, OnInit} from '@angular/core';
import {Product} from '../../entities/product';
import {IAuthenticationService} from '../../services/authentication/iauthentication.service';
import {IProductService} from '../../services/product/iproduct.service';
import {ICategoryService} from '../../services/category/icategory-service.service';
import {IImageUploadService} from "../../services/imageUpload/iimage-upload.service";

/**
 * The default location of the picture. when uploaded to the server.
 * @type {string}
 */
const URL_PHOTO: string = 'http://localhost:8090/CityMarket/image/';

/**
 * Component creates a new product.
 */
@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  private uploadImage: boolean = false;
  /**
   * List of all products stored in the system.
   */
  public products: Product[];
  /**
   * A new product to be added to the application.
   */
  public product: Product;
  /**
   * Contains a list of files selected to be added as a picture to the product.
   */
  public fileList: FileList;
  /**
   * Messages to the user during the execution of the application.
   */
  public message: string;


  constructor(@Inject('authenticationService') private authenticationService: IAuthenticationService,
              @Inject('productService') private productService: IProductService,
              @Inject('categoryService') private categoryService: ICategoryService,
              @Inject('imageService') private imageService: IImageUploadService) {
  }

  /**
   * When the component is initialized, a new product is created to add to the application.
   */
  ngOnInit() {
    this.product = new Product();
    this.productService.getAllProducts()
      .then(resp => this.products = resp)
      .catch(error => this.message = error);
  }

  /**
   * All product fields are filled in and the product is sent to the service for storage.
   */
  save() {
    this.product.stock = this.authenticationService.getUser().stock;
    this.product.category = this.categoryService.getSelectedCategory();
    this.saveProduct();
    if (this.uploadImage)
      this.imageService.uploadImage(this.fileList)
        .then(resp => {
          this.message = resp;
        })
        .catch(error => this.message = error);
  }

  /**
   * The image in the image list is saved.
   * @param event
   */
  selectImage(event) {
    this.uploadImage = true;
    this.fileList = event.target.files;
    this.product.urlPhoto = URL_PHOTO + this.fileList[0].name;
  }

  /**
   * The product is sent to the service for storage.
   */
  saveProduct() {
    this.productService.saveProduct(this.product)
      .then(resp => this.message = resp)
      .catch(error => this.message = error);
  }
}
