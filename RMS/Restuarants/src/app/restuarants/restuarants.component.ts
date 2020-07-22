import { Component, OnInit } from '@angular/core';
import { RestuarantService } from '../services/restuarant.service';
import { Router } from '@angular/router';
import { Restuarant } from '../classes/Restuarant';
import { ReturnResult } from '../classes/ReturnResult';

@Component({
  selector: 'app-restuarants',
  templateUrl: './restuarants.component.html',
  styleUrls: ['./restuarants.component.css']
})
export class RestuarantsComponent implements OnInit {

  selectedRestId: number;
  restuarants: Restuarant[];
  selectedRestuarant: Restuarant;

  returnResult: ReturnResult;
  showAlert: boolean = false;
  alertMsg: string;

  constructor(
    private restuarantService: RestuarantService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.getAllRestuarants();
  }

  add() {
    this.router.navigate(['addRestuarant']);
  }

  public delete(restId: number) {
    console.log(` Deleting ${restId}`);
    this.restuarantService.deleteRestuarant(restId).subscribe(
      response => {
        this.returnResult = response;
        if(this.returnResult.status === 'SUCCESS') {
          this.getAllRestuarants();
        } else {
            this.showAlert = true;
            this.selectedRestId = restId;
            console.log(`${this.returnResult.message}`)
            this.alertMsg = this.returnResult.message;
        }
      }
    );
  }

  public getAllRestuarants() {
    this.restuarantService.getRestuarants().subscribe(
      response => {
        this.restuarants = response;
      }
    );
  }

}
