import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OrderHomeComponent} from './order/order-home.component';
import {OrderListComponent} from './orders-list/order-list.component';
import {AuthGuard} from '../login/guard/auth.guard';
import {AuthorizationGuard} from '../login/guard/authorization.guard';
import {CreateNewComponent} from './create-new/create-new.component';
import {NewOrderGuard} from './new-order.guard';
import {OrderDetailComponent} from './order-detail/order-detail.component';

const routes: Routes = [
  {
    path: '',
    component: OrderHomeComponent,
    canActivate: [AuthGuard, AuthorizationGuard],
    data: {roles: ['CASHIER', 'SENIOR_CASHIER']},
    children: [
      {
        path: 'checkout',
        component: CreateNewComponent,
        resolve: {createdOrder: NewOrderGuard}
      },
      {
        path: '',
        component: OrderListComponent,
        pathMatch: 'full'
      },
      {
        path: 'details/:id',
        component: OrderDetailComponent,
        pathMatch: 'full'
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule {
}
