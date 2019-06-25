import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PageableOrders} from './data/PageableOrders';
import {Order} from './data/Order';
import {OrderEntry} from './data/OrderEntry';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {
  }

  public getOrders(size: number, page: number): Observable<PageableOrders> {
    const href = 'http://localhost:8888/cashmachine/api/receipt';
    const requestUrl =
      `${href}?page=${page}&size=${size}`;

    return this.http.get<PageableOrders>(requestUrl);
  }

  public getOrderWithId(id: string): Observable<Order> {
    return this.http.get<Order>(`http://localhost:8888/cashmachine/api/receipt/${id}`);
  }

  public createNewOrder(): Observable<Order> {
    return this.http.post<Order>('http://localhost:8888/cashmachine/api/receipt', {});
  }

  public addProductToOrderWithQuantity(orderId: string, productCode: string, quantity: number): Observable<Order> {
    return this.http.post<Order>(`http://localhost:8888/cashmachine/api/receipt/${orderId}`, {productCode, quantity});
  }

  public updateEntry(entry: OrderEntry): Observable<OrderEntry> {
    return this.http.put<OrderEntry>(`http://localhost:8888/cashmachine/api/receipt-entries/${entry.id}`, entry);
  }

  public updateOrderStatus(orderId: string, status: string): Observable<Order> {
    return this.http.put<Order>(`http://localhost:8888/cashmachine/api/receipt/${orderId}`, {status});
  }
  public cancellOrder(orderId: string): Observable<Order> {
    return this.http.put<Order>(`http://localhost:8888/cashmachine/api/receipt/cancel/${orderId}`, {});
  }
}
