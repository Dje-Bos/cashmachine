import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OrderHomeComponent} from './order/order-home.component';
import {OrderListComponent} from './orders-list/order-list.component';
import {AuthGuard} from '../login/guard/auth.guard';
import {AuthorizationGuard} from '../login/guard/authorization.guard';
import {CreateNewComponent} from './create-new/create-new.component';
import {NewOrderGuard} from './new-order.guard';

const routes: Routes = [
  {
    path: 'orders',
    component: OrderHomeComponent,
    canActivate: [AuthGuard, AuthorizationGuard],
    data: {roles: ['CASHIER', 'SENIOR_CASHIER']},
    children: [
      {
        path: 'checkout',
        component: CreateNewComponent,
        resolve: { createdOrder: NewOrderGuard}
      },
      {
        path: '',
        component: OrderListComponent,
        pathMatch: 'full'
      }
      ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule { }
