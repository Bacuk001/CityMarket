import {Component, Inject, OnInit} from '@angular/core';
import {IDescriptionService} from "../../services/description/idescription.service";
import {IProductService} from "../../services/product/iproduct.service";
import {Product} from "../../entities/product";
import {Description} from "../../entities/description";
import {Router} from "@angular/router";

/**
 * Еhe component contains a functional for creating a description for the product.
 */
@Component({
    selector: 'app-description-product-create',
    templateUrl: './description-product-create.component.html',
    styleUrls: ['./description-product-create.component.css']
})
export class DescriptionProductCreateComponent implements OnInit {
    /**
     * Contain product to which descriptions are added.
     */
    public product: Product;
    /**
     * List of descriptions for this product.
     */
    public descriptions: Description[];
    /**
     * Description to create.
     */
    public descriptionCreate: Description;
    /**
     * Contains messages sent to the user about the progress of the program.
     */
    public message: string;
    /**
     * If the value is correct, the change button is visible, otherwise there is not visible.
     */
    public visibleChange: boolean;

    public indexDescriptionSelect: number;

    constructor(@Inject('descriptionService') private descriptionService: IDescriptionService,
                @Inject('productService') private productService: IProductService,
                private router: Router) {
    }

    /**
     * When the component is initialized, it is extracted from the product service, the product in
     * which you need to add and modify descriptions. and load all descriptions that have already
     * been created.
     */
    ngOnInit() {
        this.descriptionCreate = new Description();
        this.product = this.productService.getSelectProduct();
        this.descriptionService.getDescriptionsByProduct(this.product)
            .then(resp => this.descriptions = resp)
            .catch(error => this.message = error);
        this.visibleChange = false;
    }

    /**
     * Adds the created description to the description list.
     */
    addDescription() {
        this.descriptions.push(this.descriptionCreate);
        this.descriptionCreate = new Description();
    }

    /**
     * Transfers a list of descriptions to the product in the service for saving.
     */
    saveDescriptions() {
        for (let description of this.descriptions) description.product = this.product
        this.descriptionService.saveDescription(this.descriptions)
            .then(resp => this.message = resp)
            .catch(error => this.message = error);
    }

    /**
     * The method selects the description for the change.
     *
     * @param {Description} description
     * @param {number} indexSelectDescription
     */
    selectedEditDescription(description: Description, indexSelectDescription: number) {
        this.descriptionCreate = description;
        this.indexDescriptionSelect = indexSelectDescription;
        this.visibleChange = true;
    }

    /**
     * The method clears the field for entering changes.
     */
    changeDescription() {
        this.visibleChange = false;
        this.descriptionCreate = new Description();
    }

    /**
     * Delete select description from list descriptions.
     */
    deleteDescription() {
        this.descriptions.splice(this.indexDescriptionSelect, 1);
        this.descriptionCreate = new Description();
    }

    /**
     * Cancels editing product descriptions.
     */
    close() {
        this.router.navigateByUrl('category/listProduct')
            .then(() => this.message = 'Ошибка маршрута.');
    }
}
