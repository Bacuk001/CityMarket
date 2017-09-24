import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {LeftNavigateComponent} from './category-navigate/left-navigate.component';
import {AboutComponent} from './about/about.component';
import {DeliveryComponent} from './delivery/delivery.component';
import {InstallmentplanComponent} from './installmentplan/installmentplan.component';
import {ViewProductListComponent} from './category-navigate/view-product-list/view-product-list.component';
import {AddCategoryComponent} from './add-category/add-category.component';
import {AddProductComponent} from './category-navigate/add-product/add-product.component';
import {CreateUserComponent} from './create-user/create-user.component';
import {SignStockMarketComponent} from './sign-stock-market/sign-stock-market.component';
import {CreateStockComponent} from './create-stock/create-stock.component';
import {CreateMarketComponent} from './create-market/create-market.component';
import {AddPriceComponent} from './category-navigate/add-price/add-price.component';
import {OrderUserComponent} from './category-navigate/order-user/order-user.component';
import {ListOrderMarketComponent} from "./list-order-market/list-order-market.component";
import {DescriptionProductCreateComponent} from "./category-navigate/description-product-create/description-product-create.component";
import {DescriptionProductComponent} from "./category-navigate/description-product/description-product.component";

/**
 * Nested routing in the list of banks.
 */
const itemRoutes: Routes = [
  {path: '', component: InstallmentplanComponent},
  {path: 'listProduct', component: ViewProductListComponent},
  {path: 'addProduct', component: AddProductComponent},
  {path: 'addPrice', component: AddPriceComponent},
  {path: 'addDescription', component: DescriptionProductCreateComponent},
  {path: 'viewDescription', component: DescriptionProductComponent}

];
/**
 * Routing of the main component. The main directions of application fixing.
 * The first "banks" list displays a list of all registered banks.
 */
export const appRoutes: Routes = [
  {path: 'category', component: LeftNavigateComponent, children: itemRoutes},
  {path: 'about', component: AboutComponent},
  {path: 'delivery', component: DeliveryComponent},
  {path: 'installment', component: InstallmentplanComponent},
  {path: 'addCategory', component: AddCategoryComponent},
  {path: 'addUser', component: CreateUserComponent},
  {path: 'signStock', component: SignStockMarketComponent},
  {path: 'createStock', component: CreateStockComponent},
  {path: 'createMarket', component: CreateMarketComponent},
  {path: 'order', component: OrderUserComponent},
  {path: 'orderListMarket', component: ListOrderMarketComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutes {
}
