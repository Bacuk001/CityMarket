import {Product} from "../../entity/product";
import {Description} from "../../entity/description";

export interface IDescriptionService {
  getDescriptionByProduct(product: Product);

  saveDescription(description: Description[]);
}
