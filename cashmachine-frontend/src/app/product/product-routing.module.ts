import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from '../login/guard/auth.guard';
import {AuthorizationGuard} from '../login/guard/authorization.guard';
import {ProductHomeComponent} from './product-home/product-home.component';
import {ProductListComponent} from './product-list/product-list.component';
import {ProcuctDetailComponent} from './procuct-detail/procuct-detail.component';
import {UpdateProductComponent} from './update-product/update-product.component';

const routes: Routes = [{
  path: '',
  component: ProductHomeComponent,
  canActivate: [AuthGuard, AuthorizationGuard],
  data: {roles: ['ADMIN', 'MERCHANDISE']},
  children: [
    {
      path: 'create',
      component: ProcuctDetailComponent
    },
    {
      path: ':id',
      component: UpdateProductComponent
    },
    {
      path: '',
      component: ProductListComponent,
      pathMatch: 'full'
    }
  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)]
})
export class ProductRoutingModule {
}
