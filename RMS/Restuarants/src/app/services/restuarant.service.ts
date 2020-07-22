import { Injectable, OnInit } from '@angular/core';
import { Restuarant } from '../classes/Restuarant';
import { HttpClient } from '@angular/common/http';
import { ReturnResult } from '../classes/ReturnResult';
import { API_URL } from 'src/app.constants';
import 'rxjs/Rx';

@Injectable({
  providedIn: 'root'
})
export class RestuarantService implements OnInit{

  restIds: string[] = [];
  restuarants: string[] = [];
  filteredResturants: string[] = [];
  filteredRestIds: string[] = [];

  restMap = new Map<number,string>();

  constructor(private http: HttpClient) {
    this.getRestIdNameMap();
   }

  ngOnInit(): void {

  }

  public getRestuarants() {
    return this.http.get<Restuarant[]>(`${API_URL}/RMS/getAllRestuarants`);
  }

  public getFilteredRestIds(event: any): string[] {
    this.filteredRestIds = [];
    for(let i=0;i<this.restIds.length;i++) {
      let restId = this.restIds[i];
      if(restId.toLowerCase().indexOf(event.query.toLowerCase()) == 0) {
        this.filteredRestIds.push(restId);
      }
    }
    return this.filteredRestIds;
  }


  async getRestuarantsDetails() {
   const value = await this.http.get<Map<number,string>>(`${API_URL}/RMS/getAllRestuarantsIdMap`)
   .toPromise().then(
      result => {
        const map = new Map(Object.entries(result));
        for(let [key,value] of map) {
          let keyNum = parseInt(key);
          this.restMap.set(keyNum,value);
          this.restIds.push(keyNum.toString());
        }
      }
    );
   return value;
  }

  public getRestIdNameMap() {
    if(this.restMap.size == 0 ) {
      this.getRestuarantsDetails();
    }
    return this.restMap;
  }


  public getRestName(restId: number): string {
    if(restId === null) {
      return '';
    }
    let restMap = this.getRestIdNameMap();
    let num = parseInt(restId.toString());
    let restName = restMap.get(num);

    console.log(`restName: ${restName}`);
    return restName;
  }

  public getFilteredResturants(event: any): string[] {
    this.filteredResturants = [];
    for(let i=0;i<this.restuarants.length;i++) {
      let rest = this.restuarants[i];
      if(rest.toLowerCase().indexOf(event.query.toLowerCase()) == 0) {
          this.filteredResturants.push(rest);
      }
    }
    return this.filteredResturants;
  }

  public saveRestuarant(restuarant: Restuarant) {
    return this.http.post<ReturnResult>(`${API_URL}/RMS/addRestuarant/${restuarant.restId}`,restuarant);
  }

  public deleteRestuarant(restId: number) {
    return this.http.delete<ReturnResult>(`${API_URL}/RMS/deleteRestuarant/${restId}`);
  }
}
