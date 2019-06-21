import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {Order} from './data/Order';
import {UserProfile} from '../data/UserProfile';
import {OrderService} from './order.service';

@Injectable({
  providedIn: 'root'
})
export class NewOrderGuard implements Resolve<Order> {
  constructor(private orderService: OrderService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Order> {
    return this.orderService.createNewOrder();
  }
}
