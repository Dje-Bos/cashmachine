import {Component, OnInit} from '@angular/core';
import {OrderEntry} from '../data/OrderEntry';
import {FormControl, FormGroup} from '@angular/forms';
import index from '@angular/cli/lib/cli';
import {ActivatedRoute, Router} from '@angular/router';
import {Order} from '../data/Order';
import {Product} from '../data/Product';
import {ProductService} from '../product.service';
import {OrderService} from '../order.service';


@Component({
  selector: 'app-create-new',
  templateUrl: './create-new.component.html',
  styleUrls: ['./create-new.component.css']
})
export class CreateNewComponent implements OnInit {
  private createdOrder: Order;
  private suggestedProducts: Product[] = [];
  private entries: OrderEntry[] = [];
  entryForm = new FormGroup({
    quantity: new FormControl(''),
    index: new FormControl('')
  });
  focusOnSearch = true;
  productSearchForm = new FormGroup({
    code: new FormControl('')
  });

  constructor(private orderService: OrderService, private route: ActivatedRoute, private productService: ProductService, private router: Router) {

  }

  ngOnInit() {
    if (this.entries.length > 0) {
      const orderEntry = this.entries[this.entries.length - 1];
      this.entryForm.setValue(
        {
          quantity: orderEntry.quantity,
          index: this.entries.length - 1
        }
      );
    }
    this.createdOrder = this.route.snapshot.data['createdOrder'];
    if (this.createdOrder.entries) {
      this.entries = this.createdOrder.entries;
    }
    this.onProductSearchFormChanges();
  }

  onQuantitySubmit() {
    console.log('Quantity changed value = ' + this.entryForm.value.quantity);
    let orderEntry = this.entries[this.entryForm.value.index];
    orderEntry.quantity = this.entryForm.value.quantity;
    this.orderService.updateEntryForOrder(this.createdOrder.id, orderEntry).subscribe(this.getOrder());
  }

  onProductSearchFormChanges() {
    this.productSearchForm.get('code').valueChanges.subscribe((value) => {
      if (value.length >= 3) {
        this.productService.suggestProduct(value).subscribe((products: Product[]) => {
          this.suggestedProducts = products;
        });
      } else {
        this.suggestedProducts = [];
      }
    });
  }

  addProductToOrder(code: string) {
    console.log('adding productCode ot order ' + code);
    this.setFocusOnSearch(false);
    this.orderService.addProductToOrderWithQuantity(this.createdOrder.id, code, 1).subscribe(this.getOrder());
  }

  private getOrder() {
    return (order: Order) => {
      this.createdOrder = order;
      if (order.entries) {
        this.entries = order.entries;
      }
    };
  }

  setFocusOnSearch(focusOnSearch: boolean) {
    this.focusOnSearch = focusOnSearch;
  }

  finishOrderCreation() {
    this.orderService.updateOrderStatus(this.createdOrder.id, 'CLOSED').subscribe((order) => {
      this.router.navigate([''], {relativeTo: this.route});
    });
  }

  finishOrderCreationAndStartNew() {
    this.orderService.updateOrderStatus(this.createdOrder.id, 'CLOSED').subscribe((order) => {
      window.location.reload();
    });
  }
}
