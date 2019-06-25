import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {OrderRoutingModule} from './order-routing.module';
import {OrderHomeComponent} from './order/order-home.component';
import {OrderListComponent} from './orders-list/order-list.component';
import {
  MatButtonModule,
  MatFormFieldModule, MatIconModule, MatInputModule,
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
import { OrderDetailComponent } from './order-detail/order-detail.component';

@NgModule({
  declarations: [
    OrderHomeComponent,
    OrderListComponent,
    CreateOrderComponent,
    CreateNewComponent,
    OrderDetailComponent,
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
    ReactiveFormsModule,
    MatIconModule
  ]
})
export class OrderModule {
}
