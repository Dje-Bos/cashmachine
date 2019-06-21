import {HttpClient} from '@angular/common/http';
import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-orders',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
/**
 * @title Table retrieving data through HTTP
 */
export class OrderListComponent implements AfterViewInit {

  displayedColumns: string[] = ['id', 'status', 'cashier', 'total', 'creationTime'];
  orderDatabase: OrderDatabase | null;
  data: Order[] = [];

  resultsLength = 0;
  isLoadingResults = true;

  @ViewChild(MatPaginator)
  paginator: MatPaginator;
  @ViewChild(MatSort)
  sort: MatSort;

  constructor(private _httpClient: HttpClient) {}

  ngAfterViewInit() {
    this.orderDatabase = new OrderDatabase(this._httpClient);

    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.orderDatabase.getOrders(
            this.sort.active, this.sort.direction, this.paginator.pageIndex);
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.resultsLength = data.total_count;

          return data.items;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return observableOf([]);
        })
      ).subscribe(data => this.data = data);
  }
}

export interface PageableOrders {
  items: Order[];
  total_count: number;
}

export interface Order {
  creationTime: string;
  id: string;
  status: string;
  total: string;
  cashier: string;
}

/** An example database that the data source uses to retrieve data for the table. */
export class OrderDatabase {
  constructor(private _httpClient: HttpClient) {}

  getOrders(sort: string, order: string, page: number): Observable<PageableOrders> {
    const href = 'http://localhost:8888/api/orders';
    const requestUrl =
      `${href}?sort=${sort}&order=${order}&page=${page + 1}`;

    return this._httpClient.get<PageableOrders>(requestUrl);
  }
}


