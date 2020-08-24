import { Injectable } from '@angular/core';
import {
  HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest
} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/do';
import { Router } from '@angular/router';

@Injectable()
export class CustomCorsInterceptor implements HttpInterceptor {

  constructor(private router: Router) {

  }
  // tslint:disable:curly
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authHeader = 'Not Authorized';
    if (localStorage.getItem('user_token'))
      authHeader = 'Bearer ' + localStorage.getItem('user_token');
    if(req.headers.get("Content-Type") == null)
      req.headers.set("Content-Type","application/json");
    const request = req.clone(
      {headers:req.headers
        .set("Access-Control-Allow-Origin","*")
        .set("Access-Control-Allow-Methods","GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH")
        .set("Access-Control-Allow-Credentials","true")
        .set("Authorization",authHeader)
      });
    return next.handle(request).do(() => {

      },
      (error: any) => {
        if (error instanceof HttpErrorResponse && error.status === 401) {
          localStorage.removeItem('user_token');
          localStorage.removeItem('user_name');
          localStorage.removeItem('user_id');
          this.router.navigate(['/login']);
        }
      });
  }
}
