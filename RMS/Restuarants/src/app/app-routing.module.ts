import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './error/error.component';
import { TablesComponent } from './tables/tables.component';
import { ProductsComponent } from './products/products.component';
import { OrdersComponent } from './orders/orders.component';
import { AddTableComponent } from './add-table/add-table.component';
import { RestuarantsComponent } from './restuarants/restuarants.component';
import { AddProductComponent } from './add-product/add-product.component';
import { AddOrderComponent } from './add-order/add-order.component';
import { LogoutComponent } from './logout/logout.component';
import { RouteGuardService } from './services/route-guard.service';
import { AddRestuarantComponent } from './add-restuarant/add-restuarant.component';


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'tables', component: TablesComponent, canActivate: [RouteGuardService]},
  {path: 'products', component: ProductsComponent, canActivate: [RouteGuardService]},
  {path: 'orders', component: OrdersComponent, canActivate: [RouteGuardService]},
  {path: 'restuarants', component: RestuarantsComponent, canActivate: [RouteGuardService]},
  {path: 'logout', component: LogoutComponent, canActivate: [RouteGuardService]},

  {path: 'addTable', component: AddTableComponent, canActivate: [RouteGuardService]},
  {path: 'editTable/:restId/:restName/:capacity/:availableTable', component: AddTableComponent, canActivate: [RouteGuardService]},

  {path: 'addProduct', component: AddProductComponent, canActivate: [RouteGuardService]},
  {path: 'editProduct/:restId/:restName/:product/:price/:description', component: AddProductComponent},

  {path: 'addOrder', component: AddOrderComponent, canActivate: [RouteGuardService]},
  {path: 'editOrder/:restId/:restName/:billNo/:totProd/:totAmount/:paidStatus', component: AddOrderComponent, canActivate: [RouteGuardService]},

  {path: 'addRestuarant', component: AddRestuarantComponent, canActivate: [RouteGuardService]},

  {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
