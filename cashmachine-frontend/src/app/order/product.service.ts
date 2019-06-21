import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from './data/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public suggestProduct(query: string): Observable<Product[]> {
    return this.http.get<Product[]>(`http://localhost:8888/cashmachine/api/products?query=${query}`);
  }
}
