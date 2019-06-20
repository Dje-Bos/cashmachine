import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './login/guard/auth.guard';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {AuthorizationGuard} from './login/guard/authorization.guard';
import {RedirectGuard} from './redirect/redirect.guard';
import {StubComponent} from './stub/stub/stub.component';

const appRoutes: Routes = [
  {
    path: 'orders',
    loadChildren: () => import('./order/order.module').then(mod => mod.OrderModule),
    canActivate: [AuthGuard, AuthorizationGuard],
    data: {roles: ['CASHIER', 'ADMIN']},
  },
  {
    path: '',
    component: StubComponent,
    canActivate: [RedirectGuard],
    pathMatch: 'full'
  },
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
