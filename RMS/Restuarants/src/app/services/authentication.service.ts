import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map} from 'rxjs/operators';
import { AUTH_API_URL } from 'src/app.constants';

export const AUTH_USER = 'authenticatedUser';
export const TOKEN = 'token';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  public authenticate(userName: string, password: string): boolean {
    if(userName === 'admin' && password === 'admin') {
        sessionStorage.setItem(AUTH_USER,userName);
        return true;
    }
    return false;
  }

  // Get Welcome Message with JWT
  executeJWTAuthService(username: string, password: string) {

    return this.http.post<any>(
      `${AUTH_API_URL}` ,
        {
          username,
          password
        }).pipe(
            map(data => {
              sessionStorage.setItem(AUTH_USER,username);
              sessionStorage.setItem(TOKEN,`Bearer ${data.token}`);
              return data;
            })
        );
  }

  getAuthenticatedUser() {
    return  sessionStorage.getItem(AUTH_USER);
  }

  getAuthenticatedToken() {
    if(this.getAuthenticatedUser()) {
       return sessionStorage.getItem(TOKEN);
    }
  }

  isUserLoggedIn() {
      let user =  sessionStorage.getItem('authenticatedUser');
      return (user !== null);
  }

  logoutUser(): void {
    sessionStorage.removeItem(AUTH_USER);
    sessionStorage.removeItem(TOKEN);
  }
}
