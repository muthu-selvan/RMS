import { Injectable } from '@angular/core';
import { Order } from '../classes/Order';
import { HttpClient } from '@angular/common/http';
import { ReturnResult } from '../classes/ReturnResult';
import { API_URL } from 'src/app.constants';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  orders: Order[] = [];

  orderCols: any[] = [
    {field:'billNo', header:'Bill Number'},
    {field:'restName', header:'Restuarant Name'},
    {field:'restId', header:'Restuarant Id'},
    {field:'totalProducts', header:'Quantity'},
    {field:'paidStatus', header:'Paid Status'},

  ];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  public getOrders() {
      return this.http.get<Order[]>(`${API_URL}/RMS/getAllOrders`);
  }

  public getOrderHeaders(): any[] {
      return this.orderCols;
  }

  public deleteOrder(order: Order): any {
    return this.http.delete<ReturnResult>(`${API_URL}/RMS/deleteOrder/${order.billNo}`);
  }

  public saveOrder(order: Order) {
    return this.http.post<ReturnResult>(`${API_URL}/RMS/addOrder/${order.billNo}`,order);
  }

}
