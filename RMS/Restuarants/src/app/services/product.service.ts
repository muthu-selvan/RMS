import { Injectable } from '@angular/core';
import { Product } from '../classes/Product';
import { ProductsComponent } from '../products/products.component';
import { HttpClient } from '@angular/common/http';
import { ReturnResult } from '../classes/ReturnResult';
import { API_URL } from 'src/app.constants';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  products: Product[] = [
    {productName:'Grilled Chicken', price:500, restName:'Rushi', restId:1,description:'Very Nice'},
    {productName:'Mutton Biriyani', price:300, restName:'Hotel Pushpa', restId:2,description:'Very Good'},
    {productName:'Chicken Kabal', price:250, restName:'Mano', restId:3,description:'Good'},
    {productName:'Grilled Mushroom', price:500, restName:'Gowri Sankar', restId:4,description:'Avarage'},
    {productName:'Veg Biriyani', price:500, restName:'Hotel Ganga', restId:5,description:'Very Nice'},

  ];

  prodCols: any[] = [
    {field:'productName', header:'Product Name'},
    {field:'price', header:'Price'},
    {field:'restName', header:'Restuarant Name'},
    {field:'restId', header:'Restuarant Id'},
    {field:'description', header:'Description'}
  ];

  constructor(private http: HttpClient) { }

  public getProducts(){
      return this.http.get<Product[]>(`${API_URL}/RMS/getAllProducts`);
  }

  public getProductHeaders(): any[] {
    return this.prodCols;
  }

  public deleteProduct(product: Product) {
      return this.http.delete<ReturnResult>(`${API_URL}/RMS/deleteProduct/${product.restId}`);
  }

  public saveProduct(product: Product) {
    return this.http.post<ReturnResult>(`${API_URL}/RMS/addProduct/${product.restId}`,product);
  }
}
