import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';
import {FormsModule} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {AppComponent} from './app.component';
import {AppRoutes} from './app.routes';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {CategoryNavigateComponent} from './category-navigate/category-navigate.component';
import {RightNavigateComponent} from './right-navigate/right-navigate.component';
import {TopNavigateComponent} from './top-navigate/top-navigate.component';
import {BodyApplicationComponent} from './body-application/body-application.component';
import {CategoryService} from './services/category/category-service.service';
import {MarketService} from './services/market/market.service';
import {BootstrapGridModule} from 'ng2-bootstrap-grid';
import {StartPageComponent} from './startPage/startPageComponent.component';
import {ViewProductListComponent} from './category-navigate/view-product-list/view-product-list.component';
import {ProductService} from './services/product/product.service';
import {AddProductComponent} from './category-navigate/add-product/add-product.component';
import {AddCategoryComponent} from './add-category/add-category.component';
import {AuthenticationService} from './services/authentication/authentication.service';
import {AddPriceComponent} from './category-navigate/add-price/add-price.component';
import {SignStockMarketComponent} from './sign-stock-market/sign-stock-market.component';
import {CreateMarketComponent} from './create-market/create-market.component';
import {CreateUserComponent} from './create-user/create-user.component';
import {AccessService} from './services/access/access.service';
import {CreateStockComponent} from './create-stock/create-stock.component';
import {StockService} from './services/stock/stock.service';
import {PriceService} from './services/price/price.service';
import {OrderUserComponent} from './category-navigate/order-user/order-user.component';
import {OrderService} from './services/order/order.service';
import {ListOrderMarketComponent} from './list-order-market/list-order-market.component';
import {DescriptionProductComponent} from './category-navigate/description-product/description-product.component';
import {DescriptionProductCreateComponent} from './category-navigate/description-product-create/description-product-create.component';
import {DescriptionService} from './services/description/description.service';
import {ImageUploadService} from './services/imageUpload/image-upload.service';
import { PaginationComponent } from './pagination/pagination.component';
import { FooterComponent } from './footer/footer.component';

export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, '/CityMarket/localisation/', '.json');
}

const translModel = {
  loader: {
    provide: TranslateLoader,
    useFactory: (createTranslateLoader),
    deps: [HttpClient]
  }
};

@NgModule({
  declarations: [
    AppComponent,
    StartPageComponent,
    CategoryNavigateComponent,
    RightNavigateComponent,
    TopNavigateComponent,
    BodyApplicationComponent,
    ViewProductListComponent,
    AddProductComponent,
    AddCategoryComponent,
    SignStockMarketComponent,
    AddPriceComponent,
    CreateMarketComponent,
    CreateUserComponent,
    CreateStockComponent,
    OrderUserComponent,
    ListOrderMarketComponent,
    DescriptionProductComponent,
    DescriptionProductCreateComponent,
    PaginationComponent,
    FooterComponent
  ],
  imports: [
    FormsModule,
    HttpModule,
    AppRoutes,
    HttpClientModule,
    BrowserModule,
    TranslateModule.forRoot(translModel),
    BootstrapGridModule
  ],
  providers: [
    {provide: 'categoryService', useClass: CategoryService},
    {provide: 'marketService', useClass: MarketService},
    {provide: 'productService', useClass: ProductService},
    {provide: 'stockService', useClass: StockService},
    {provide: 'authenticationService', useClass: AuthenticationService},
    {provide: 'priceService', useClass: PriceService},
    {provide: 'orderService', useClass: OrderService},
    {provide: 'descriptionService', useClass: DescriptionService},
    {provide: 'imageService', useClass: ImageUploadService},
    AccessService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
