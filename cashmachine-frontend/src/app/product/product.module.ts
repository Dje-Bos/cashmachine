import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProductRoutingModule} from './product-routing.module';
import {ProductListComponent} from './product-list/product-list.component';
import {ProductHomeComponent} from './product-home/product-home.component';
import {
  MatButtonModule, MatCheckboxModule, MatFormFieldModule,
  MatIconModule, MatInputModule,
  MatPaginatorModule,
  MatProgressSpinnerModule, MatSnackBarModule,
  MatSortModule,
  MatTableModule
} from '@angular/material';
import {HfModule} from '../hf/hf/hf.module';
import {RouterModule} from '@angular/router';
import { ProcuctDetailComponent } from './procuct-detail/procuct-detail.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UpdateProductComponent} from './update-product/update-product.component';

@NgModule({
  declarations: [ProductListComponent, ProductHomeComponent, ProcuctDetailComponent, UpdateProductComponent],
  imports: [
    CommonModule,
    ProductRoutingModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    HfModule,
    RouterModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatCheckboxModule,
    MatSnackBarModule
  ]
})
export class ProductModule {
}
