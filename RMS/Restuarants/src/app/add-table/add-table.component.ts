import { Component, OnInit } from '@angular/core';
import { RestuarantService } from '../services/restuarant.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Table } from '../classes/Table';
import { TableService } from '../services/table.service';
import { ReturnResult } from '../classes/ReturnResult';

@Component({
  selector: 'app-add-table',
  templateUrl: './add-table.component.html',
  styleUrls: ['./add-table.component.css']
})
export class AddTableComponent implements OnInit {
  restId: number;
  restIds: string[];
  selectedResturant: string;
  restuarants: string[];
  capacity: number;
  availableTable: number;
  returnResult: ReturnResult;

  showAlert: boolean = false;
  alertMsg: string;

  constructor(
    private restuarantService: RestuarantService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private tableService: TableService
    ) { }

  ngOnInit(): void {
    let restIdObj = this.activatedRoute.snapshot.paramMap.get("restId");
    if(restIdObj !== null)
        this.restId = parseInt(restIdObj);
    this.selectedResturant = this.activatedRoute.snapshot.paramMap.get("restName");
    let capacityStr = this.activatedRoute.snapshot.paramMap.get("capacity");
    if(capacityStr !== null)
      this.capacity = parseInt(capacityStr);
    let availableTableStr = this.activatedRoute.snapshot.paramMap.get("availableTable");
    if(availableTableStr !== null)
      this.availableTable = parseInt(availableTableStr);
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

  public cancel() {
    this.router.navigate(['tables']);
  }

  public save() {
    let table = new Table(this.restId,this.selectedResturant,
      this.capacity,this.availableTable,true);
    this.tableService.saveTable(table).subscribe(
      response => {
        this.returnResult = response;
        console.log(`Result: ${this.returnResult.status}`);
        if(this.returnResult.status === 'SUCCESS') {
            this.router.navigate(['tables']);
        } else {
            this.showAlert = true;
            this.alertMsg = this.returnResult.message;
        }
      }
    );
  }

}
