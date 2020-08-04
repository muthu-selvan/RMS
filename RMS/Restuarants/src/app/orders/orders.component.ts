import { Component, OnInit } from '@angular/core';
import { Order } from '../classes/Order';
import { OrderService } from '../services/order.service';
import { Router } from '@angular/router';
import { ReturnResult } from '../classes/ReturnResult';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: Order[] = [];
  orderCols: any[] = [];
  selectedOrder: Order = null;

  returnResult: ReturnResult;
  showAlert: boolean = false;
  alertMsg: string;

  constructor(
    private orderService: OrderService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.getOrders();
    this.orderCols = this.orderService.getOrderHeaders();
  }

  public getOrders() {
    this.orderService.getOrders().subscribe(
      response => {
        this.orders = response;
      }
    );
  }

  public getOrderHeaders(): any[] {
      return this.orderService.getOrderHeaders();
  }

  addOrder() {
    this.router.navigate(['add-order']);
  }

  editOrder() {
    console.log(`this.selectedOrder.paidStatus : ${this.selectedOrder.paidStatus}`);
    this.router.navigate(['edit-order',
    this.selectedOrder.restId,
    this.selectedOrder.restName,
    this.selectedOrder.billNo,
    this.selectedOrder.totalProducts,
    this.selectedOrder.totalAmount,
    this.selectedOrder.paidStatus
  ]);
  }

  public deleteOrder() {
    this.showAlert = false;
    this.orderService.deleteOrder(this.selectedOrder).subscribe(
      response => {
        this.returnResult = response;
        if(this.returnResult.status === 'SUCCESS') {
            this.getOrders();
        } else {
            this.showAlert = true;
            this.alertMsg = this.returnResult.message;
        }
      }
    );
  }

}
