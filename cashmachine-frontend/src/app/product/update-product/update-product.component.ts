import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {MatSnackBar} from '@angular/material';
import {ProductService} from '../../order/product.service';
import {Product} from '../../order/data/Product';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {switchMap} from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  private product: Product;
  productForm: FormGroup;

  constructor(private productService: ProductService, private  snackBar: MatSnackBar, private route: ActivatedRoute, private location: Location) {
  }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.productService.getByCode(params.get('id')))
    ).subscribe((prod) => {
      this.product = prod; console.log(prod.active);
    });
    this.productForm = new FormGroup(
      {
        code: new FormControl(''),
        active: new FormControl(''),
        stock: new FormControl(''),
        price: new FormControl(''),
        name: new FormControl(''),
      }
    );
  }

  updateProduct() {
    if (!this.productForm.invalid) {
      let prod: Product = this.productForm.value;
      this.productForm.disable();
      this.productService.updateProductByCode(prod.code, prod).subscribe((product) => {
        this.snackBar.open('Updated ' + product.code, 'Dismiss', {
          duration: 2000
        });
        this.productForm.reset();
        this.productForm.enable();
      },
        (error) => this.productForm.enable());
    } else {
      this.snackBar.open('Form is invalid', 'Dismiss', {
        duration: 2000
      });
      this.productForm.enable();
    }

  }

  goBack() {
    this.location.back();
  }
}

