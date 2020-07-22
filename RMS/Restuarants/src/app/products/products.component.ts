import { Component, OnInit } from '@angular/core';
import { Product } from '../classes/Product';
import { ProductService } from '../services/product.service';
import { Router } from '@angular/router';
import { ReturnResult } from '../classes/ReturnResult';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[] = [];
  prodCols: any[] = [];
  selectedProduct: Product = null;

  returnResult: ReturnResult;
  showAlert: boolean = false;
  alertMsg: string;

  constructor(
    private productService: ProductService,
    public router: Router
    ) { }

  ngOnInit(): void {
      this.getProducts();
      this.prodCols = this.productService.getProductHeaders();
  }


  getProducts() {
    this.productService.getProducts().subscribe(
      response => {
        this.products = response;
      }
    );
  }

  public addProduct() {
      this.router.navigate(['addProduct']);
  }

  public editProduct() {
    this.router.navigate(['editProduct',
     this.selectedProduct.restId,
     this.selectedProduct.restName,
     this.selectedProduct.productName,
     this.selectedProduct.price,
     this.selectedProduct.description
    ]);
  }

  public deleteProduct() {
    this.productService.deleteProduct(this.selectedProduct).subscribe(
      response => {
        this.returnResult = response;
        if(this.returnResult.status === 'SUCCESS') {
            this.getProducts();
        } else {
            this.showAlert = true;
            this.alertMsg = this.returnResult.message;
        }
      }
    );
  }

}
