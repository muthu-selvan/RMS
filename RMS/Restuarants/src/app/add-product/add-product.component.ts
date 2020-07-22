import { Component, OnInit } from '@angular/core';
import { Product } from '../classes/Product';
import { RestuarantService } from '../services/restuarant.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from '../services/product.service';
import { ReturnResult } from '../classes/ReturnResult';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  restId: number;
  restIds: string[];
  product: string;
  price: number;
  selectedResturant: string;
  restuarants: string[];
  description: string;

  returnResult: ReturnResult;
  showAlert: boolean = false;
  alertMsg: string;

  constructor(
    private restuarantService: RestuarantService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private productService: ProductService
  ) { }

  ngOnInit(): void {
    let restIdObj = this.activatedRoute.snapshot.paramMap.get("restId");
    if(restIdObj !== null)
        this.restId = parseInt(restIdObj);
    this.product = this.activatedRoute.snapshot.paramMap.get('product');
    let priceStr = this.activatedRoute.snapshot.paramMap.get('price');
    if(priceStr !== null)
        this.price = parseInt(priceStr);
    this.selectedResturant = this.activatedRoute.snapshot.paramMap.get('restName');
    this.description = this.activatedRoute.snapshot.paramMap.get('description');
  }

  save() {
    let product = new Product(this.product, this.price, this.selectedResturant,this.restId, this.description);
    this.productService.saveProduct(product).subscribe(
      response => {
        this.returnResult = response;
        console.log(`Result: ${this.returnResult.status}`);
        if(this.returnResult.status === 'SUCCESS') {
            this.router.navigate(['products']);
        } else {
            this.showAlert = true;
            this.alertMsg = this.returnResult.message;
        }
      }
    );
  }

  cancel() {
    this.router.navigate(['products']);
  }

  search(event) {
    this.restuarants = [];
     if(this.restId !== null) {
       let restName = this.restuarantService.getRestName(this.restId);
       this.restuarants.push(restName);
     } else {
       this.restuarants = this.restuarantService.getFilteredResturants(event);
     }
  }

  searchRestId(event) {
    this.restIds = this.restuarantService.getFilteredRestIds(event);
  }
}
