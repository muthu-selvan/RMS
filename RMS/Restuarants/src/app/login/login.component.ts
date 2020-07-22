import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userName:string = '';
  password:string = '';
  isValidLogin:boolean = true;
  errMsg: string = '';

  constructor(
    private router: Router,
    private authService: AuthenticationService) { }

  ngOnInit(): void {
  }

  signin() {
    this.validate();
    if(!this.isValidLogin)
        return;
    this.handleJWTAuthLogin();
  }

  validate() {
    if(this.userName === null || this.userName === '') {
      this.errMsg = 'Invalid Username';
      this.isValidLogin = false;
      return
    } else if(this.password === null || this.password === '') {
      this.errMsg = 'Invalid Password';
      this.isValidLogin = false;
      return;
    }
    return;
  }

  handleJWTAuthLogin() {
    this.authService.executeJWTAuthService(this.userName, this.password)
      .subscribe(
        data => {
          this.router.navigate(['restuarants']);
          this.isValidLogin = true;
        } ,
        error => {
          this.isValidLogin = false;
          this.errMsg = 'Invalid Password';
        }
      );
  }
}
