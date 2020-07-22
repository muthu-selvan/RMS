import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterService  implements HttpInterceptor {

  constructor(private authService: AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    let basicAuthUser = this.authService.getAuthenticatedUser();
    let basicAuthHeaderString = this.authService.getAuthenticatedToken();

    if(basicAuthUser && basicAuthHeaderString) {
      request = request.clone({
        setHeaders: {
          Authorization : basicAuthHeaderString
        }
      });
    }
    return next.handle(request);
  }
}
