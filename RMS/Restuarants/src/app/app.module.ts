import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { TablesComponent } from './tables/tables.component';
import { ProductsComponent } from './products/products.component';
import { MenuComponent } from './menu/menu.component';
import { HomeComponent } from './home/home.component';
import { ErrorComponent } from './error/error.component';
import { OrdersComponent } from './orders/orders.component';
import { TableModule } from 'primeng/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ButtonModule} from 'primeng/button';
import { AddTableComponent } from './add-table/add-table.component';
import {AutoCompleteModule} from 'primeng/autocomplete';
import { FormsModule } from '@angular/forms';
import { RestuarantsComponent } from './restuarants/restuarants.component';
import { AddProductComponent } from './add-product/add-product.component';
import { AddOrderComponent } from './add-order/add-order.component';
import {CardModule} from 'primeng/card';
import { AddRestuarantComponent } from './add-restuarant/add-restuarant.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {DialogModule} from 'primeng/dialog';
import { HttpIntercepterService } from './services/http-intercepter.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    TablesComponent,
    ProductsComponent,
    MenuComponent,
    HomeComponent,
    ErrorComponent,
    OrdersComponent,
    AddTableComponent,
    RestuarantsComponent,
    AddProductComponent,
    AddOrderComponent,
    AddRestuarantComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TableModule,
    BrowserAnimationsModule,
    ButtonModule,
    AutoCompleteModule,
    FormsModule,
    CardModule,
    HttpClientModule,
    DialogModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
