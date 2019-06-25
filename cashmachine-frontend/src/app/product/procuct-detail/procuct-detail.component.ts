import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ProductService} from '../../order/product.service';
import {MatSnackBar} from '@angular/material';
import {Product} from '../../order/data/Product';
import { Location } from '@angular/common';

@Component({
  selector: 'app-procuct-detail',
  templateUrl: './procuct-detail.component.html',
  styleUrls: ['./procuct-detail.component.css']
})
export class ProcuctDetailComponent implements OnInit {
  productForm: FormGroup = new FormGroup(
    {
      code: new FormControl(''),
      active: new FormControl(''),
      stock: new FormControl(''),
      price: new FormControl(''),
      name: new FormControl(''),
    }
  );
  checked = true;


  constructor(private productService: ProductService, private snackBar: MatSnackBar, private location: Location) {
  }

  ngOnInit() {
  }

  saveProduct() {
    if (!this.productForm.invalid) {
      let prod: Product = this.productForm.value;
      this.productForm.disable();
      this.productService.createProduct(prod).subscribe((product) => {
        this.snackBar.open('Created ' + product.code, 'Dismiss', {
          duration: 2000
        });
        this.productForm.reset();
        this.productForm.enable();
      });
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
