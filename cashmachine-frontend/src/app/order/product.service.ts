import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from './data/Product';
import {PageableProducts} from '../product/data/PageableProducts';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public suggestProduct(query: string): Observable<Product[]> {
    return this.http.get<Product[]>(`http://localhost:8888/cashmachine/api/products?query=${query}`);
  }

  public getProducts(size: number, page: number, sort: string, sortOrder: string): Observable<PageableProducts> {
    const href = 'http://localhost:8888/cashmachine/api/products';
    const requestUrl =
      `${href}?page=${page}&size=${size}&sort=${sort}&sortOrder=${sortOrder.toUpperCase()}`;

    return this.http.get<PageableProducts>(requestUrl);
  }

  public deleteProductByCode(code: string): Observable<any> {
    const href = `http://localhost:8888/cashmachine/api/products?code=${code}`;
    return this.http.delete(href);
  }

  public updateProductByCode(code: string, product: Product): Observable<Product> {
    const href = `http://localhost:8888/cashmachine/api/products?code=${code}`;
    return this.http.put<Product>(href, product);
  }

  public createProduct(product: Product): Observable<Product> {
    const href = `http://localhost:8888/cashmachine/api/products`;
    return this.http.post<Product>(href, product);
  }

  public getByCode(code: string): Observable<Product> {
    const href = `http://localhost:8888/cashmachine/api/products?code=${code}`;
    return this.http.get<Product>(href);
  }
}
