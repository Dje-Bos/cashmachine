import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {concat, merge, of as observableOf} from 'rxjs';
import {catchError, map, skip, startWith, switchMap} from 'rxjs/operators';
import {OrderService} from '../order.service';
import {Order} from '../data/Order';
import {AuthService} from '../../login/service/auth-service.service';

@Component({
  selector: 'app-orders',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
/**
 * @title Table retrieving data through HTTP
 */
export class OrderListComponent implements AfterViewInit {

  displayedColumns: string[] = [];
  data: Order[] = [];

  resultsLength = 0;
  isLoadingResults = true;

  @ViewChild(MatPaginator)
  paginator: MatPaginator;
  @ViewChild(MatSort)
  sort: MatSort;

  constructor(private orderService: OrderService, private authService: AuthService) {
    if (authService.hasRole('SENIOR_CASHIER')) {
      this.displayedColumns = ['id', 'status', 'cashier', 'total', 'creationTime', 'delete'];
    } else {
      this.displayedColumns = ['id', 'status', 'cashier', 'total', 'creationTime'];
    }
  }

  ngAfterViewInit() {
    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.orderService.getOrders(
            this.paginator.pageSize, this.paginator.pageIndex);
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.resultsLength = data.totalCount;
          console.log('order items ' + data.items[0].status);
          return data.items;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return observableOf([]);
        })
      ).subscribe(data => this.data = data);
  }

  cancelOrder(orderId: string) {
    concat(this.orderService.cancellOrder(orderId), this.orderService.getOrders(this.paginator.pageSize, this.paginator.pageIndex))
      .pipe(skip(1), switchMap(() => {
          this.isLoadingResults = true;
          return this.orderService.getOrders(
            this.paginator.pageSize, this.paginator.pageIndex);
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.resultsLength = data.totalCount;
          console.log('order items ' + data.items[0].status);
          return data.items;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return observableOf([]);
        })
      ).subscribe(data => this.data = data);
  }
}

