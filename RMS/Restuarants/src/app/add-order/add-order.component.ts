import { Component, OnInit } from '@angular/core';
import { RestuarantService } from '../services/restuarant.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ReturnResult } from '../classes/ReturnResult';
import { Order } from '../classes/Order';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {
  restId: number;
  restIds: string[];
  billNo: number;
  totProd: number;
  totAmount: number;
  selectedResturant: string;
  restuarants: string[];
  paidStatus: string;
  paidStatusValues: string[] = [];

  returnResult: ReturnResult;
  showAlert: boolean = false;
  alertMsg: string;

  constructor(
    private restuarantService: RestuarantService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    let restIdObj = this.activatedRoute.snapshot.paramMap.get("restId");
    if(restIdObj !== null)
        this.restId = parseInt(restIdObj);
    this.selectedResturant = this.activatedRoute.snapshot.paramMap.get('restName');
    let billStr = this.activatedRoute.snapshot.paramMap.get('billNo');
    if(billStr !== null)
      this.billNo = parseInt(billStr);
    let totProdStr = this.activatedRoute.snapshot.paramMap.get('totProd');
    if(totProdStr !== null)
      this.totProd = parseInt(totProdStr);
    let totAmountStr = this.activatedRoute.snapshot.paramMap.get('totAmount');
    if(totAmountStr !== null)
      this.totAmount = parseInt(totAmountStr);
    this.paidStatus = this.activatedRoute.snapshot.paramMap.get('paidStatus');
  }

  save() {
    let order = new Order(this.billNo, this.selectedResturant,
      this.restId, this.totProd,this.totAmount,this.paidStatus);
    this.orderService.saveOrder(order).subscribe(
      response => {
        this.returnResult = response;
        if(this.returnResult.status === 'SUCCESS') {
            this.router.navigate(['orders']);
        } else {
            this.showAlert = true;
            this.alertMsg = this.returnResult.message;
        }
      }
    );
  }

  cancel() {
    this.router.navigate(['orders']);
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

  searchPaidStatus(event) {
    this.paidStatusValues = [
      'Yes' ,
      'No'
    ];
  }


}
