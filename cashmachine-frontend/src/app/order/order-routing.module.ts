import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OrderHomeComponent} from './order/order-home.component';
import {OrderListComponent} from './orders-list/order-list.component';

const routes: Routes = [
  {
    path: 'orders',
    component: OrderHomeComponent,
    children: [
      {
        path: '',
        component: OrderListComponent,
      }]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule { }
