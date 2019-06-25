import {Component, OnInit} from '@angular/core';
import {Order} from '../data/Order';
import {skip, switchMap} from 'rxjs/operators';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {OrderService} from '../order.service';
import {concat} from 'rxjs';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  order: Order;

  constructor(private orderService: OrderService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.orderService.getOrderWithId(params.get('id')))
    ).subscribe((ord) => {
      this.order = ord;
    });
  }

  cancelEntry(id: number) {
    concat(this.orderService.cancelEntry(id.toString()), this.orderService.getOrderWithId(this.order.id)).pipe(skip(1)).subscribe(
      (ord) => this.order = ord);
  }
}
