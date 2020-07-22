import { Component, OnInit } from '@angular/core';
import { TableService } from '../services/table.service';
import { Table } from '../classes/Table';
import { Router } from '@angular/router';
import { ReturnResult } from '../classes/ReturnResult';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css']
})
export class TablesComponent implements OnInit {

  tables: Table[] = [];
  cols: any[] = [];
  selectedTable: Table = null;

  returnResult: ReturnResult;
  showAlert: boolean = false;
  alertMsg: string;

  constructor(
    public tableService: TableService,
    public router: Router
    ) { }

  ngOnInit(): void {
    this.getTables();
    this.cols = this.tableService.getTableHeaders();
  }

  getTables() {
    this.tableService.getTablesFromRest().subscribe(
      response => {
        this.tables = response;
      }
    );
  }

  addTable() {
    this.router.navigate(['addTable']);
  }

  deleteTable() {
    this.tableService.deleteTable(this.selectedTable).subscribe(
      response => {
        this.returnResult = response;
        if(this.returnResult.status === 'SUCCESS') {
            this.getTables();
        } else {
            this.showAlert = true;
            this.alertMsg = this.returnResult.message;
        }
      }
    );
  }

  editTable() {
    this.router.navigate(['editTable',
    this.selectedTable.restId,
    this.selectedTable.restName,
    this.selectedTable.capacity,
    this.selectedTable.availableTable
  ]);
  }

}
