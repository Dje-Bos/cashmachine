import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {OrderRoutingModule} from './order-routing.module';
import {OrderHomeComponent} from './order/order-home.component';
import {OrderListComponent} from './orders-list/order-list.component';
import {
  MatButtonModule,
  MatFormFieldModule, MatInputModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSortModule,
  MatTableModule
} from '@angular/material';
import {HfModule} from '../hf/hf/hf.module';
import { CreateOrderComponent } from './create-order/create-order.component';
import { CreateNewComponent } from './create-new/create-new.component';
import {MatListModule} from '@angular/material/list';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    OrderHomeComponent,
    OrderListComponent,
    CreateOrderComponent,
    CreateNewComponent,
  ],
  imports: [
    CommonModule,
    OrderRoutingModule,
    MatTableModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatPaginatorModule,
    HfModule,
    MatButtonModule,
    MatListModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class OrderModule {
}
