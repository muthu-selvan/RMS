import { Injectable } from '@angular/core';
import { Table } from '../classes/Table';
import { HttpClient } from '@angular/common/http';
import { ReturnResult } from '../classes/ReturnResult';
import { API_URL } from 'src/app.constants';

@Injectable({
  providedIn: 'root'
})
export class TableService {

  tables: Table[] = [
    {restId:1, restName: 'Rushi', capacity:10, availableTable:30, availabilty:true} ,
    {restId:2, restName: 'Hotel Pushpa', capacity:15, availableTable:30, availabilty:false} ,
    {restId:3, restName: 'Mano', capacity:5, availableTable:30, availabilty:true} ,
    {restId:4, restName: 'Gowri Sankar', capacity:10,availableTable:30, availabilty:true} ,
    {restId:5, restName: 'Hotel Ganga', capacity:15, availableTable:30, availabilty:false} ,
    {restId:6, restName: 'Hotel Aishwarya', capacity:5, availableTable:30, availabilty:true} ,
    {restId:7, restName: 'Chettinad', capacity:10, availableTable:30, availabilty:true} ,
    {restId:8, restName: 'Nila Restuarant', capacity:15, availableTable:30, availabilty:false} ,
    {restId:9, restName: 'Le Arabia', capacity:5, availableTable:30, availabilty:true} ,
  ];

  cols: any[] = [
    {field:'restId', header:'Restuarant Id'},
    {field:'restName', header:'Restuarant Name'},
    {field:'capacity', header:'Seating Capacity'},
    {field:'availableTable', header:'Available Table'},
    {field:'availabilty', header:'Is Seat Available?'},
  ];

  constructor(private http: HttpClient) { }


  public getTables(): Table[] {
    return this.tables;
  }

  public getTablesFromRest() {
    return this.http.get<Table[]>(`${API_URL}/RMS/getAllTables`);
  }

  public getTableHeaders(): any[] {
    return this.cols;
  }

  public deleteTable(table: Table) {
    return this.http.delete<ReturnResult>(`${API_URL}/RMS/deleteTable/${table.restId}`);
  }

  public saveTable(table: Table) {
    return this.http.post<ReturnResult>(`${API_URL}/RMS/addTable/${table.restId}`,table);
  }
}
