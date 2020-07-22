import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Restuarant } from '../classes/Restuarant';
import { RestuarantService } from '../services/restuarant.service';
import { ReturnResult } from '../classes/ReturnResult';

@Component({
  selector: 'app-add-restuarant',
  templateUrl: './add-restuarant.component.html',
  styleUrls: ['./add-restuarant.component.css']
})
export class AddRestuarantComponent implements OnInit {
  restId: number;
  selectedResturant: string;

  returnResult: ReturnResult;
  showAlert: boolean = false;
  alertMsg: string;

  constructor(
    private router: Router,
    private restuarantService: RestuarantService
  ) { }

  ngOnInit(): void {
  }

  save() {
    let restuarant = new Restuarant(this.restId, this.selectedResturant);
    this.restuarantService.saveRestuarant(restuarant).subscribe(
      response => {
        this.returnResult = response;
        if(this.returnResult.status === 'SUCCESS') {
            this.router.navigate(['restuarants']);
        } else {
            this.showAlert = true;
            this.alertMsg = this.returnResult.message;
        }
      }
    );
  }

  cancel() {
    this.router.navigate(['restuarants']);
  }
}
