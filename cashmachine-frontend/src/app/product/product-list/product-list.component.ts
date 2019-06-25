import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator, MatSort} from '@angular/material';
import {concat, merge, of as observableOf} from 'rxjs';
import {catchError, map, skip, startWith, switchMap} from 'rxjs/operators';
import {Product} from '../../order/data/Product';
import {ProductService} from '../../order/product.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements AfterViewInit {

  displayedColumns: string[] = ['code', 'name', 'active', 'price', 'stock', 'update', 'delete'];
  data: Product[] = [];

  resultsLength = 0;
  isLoadingResults = true;

  @ViewChild(MatPaginator)
  paginator: MatPaginator;
  @ViewChild(MatSort)
  sort: MatSort;

  constructor(private productService: ProductService, private router: Router, private route: ActivatedRoute) {
  }

  ngAfterViewInit() {
    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.productService.getProducts(
            this.paginator.pageSize, this.paginator.pageIndex, this.sort.active, this.sort.direction);
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.resultsLength = data.totalCount;
          return data.items;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return observableOf([]);
        })
      ).subscribe(data => this.data = data);
  }

  update(code: string) {
    this.router.navigate([`${code}`], {relativeTo: this.route});
  }

  delete(code: string) {
    concat(this.productService.deleteProductByCode(code),
      this.productService.getProducts(this.paginator.pageSize, this.paginator.pageIndex, this.sort.active, this.sort.direction))
      .pipe(skip(1),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.productService.getProducts(
            this.paginator.pageSize, this.paginator.pageIndex, this.sort.active, this.sort.direction);
        }),
        map(data => {
          // Flip flag to show that loading has finished.
          this.isLoadingResults = false;
          this.resultsLength = data.totalCount;
          return data.items;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          return observableOf([]);
        }))
      .subscribe(data => this.data = data);
  }

  navigateToCreate() {
    this.router.navigate(['create'], {relativeTo: this.route});
  }
}
