import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { UserToken } from '../../model/user-token';
import { Subject } from 'rxjs/Subject';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';

@Injectable()
export class AuthenticationService {
    private url: string;
    private loggedSubject = new Subject<boolean>();
    isLoggedIn = this.loggedSubject.asObservable();
    baseUrl: string =  environment.authBaseUrl;
    constructor(private http: HttpClient, private router: Router) {

    }

    login(loginUser): Observable<UserToken> {
        this.url = this.baseUrl;
        return this.http.post<UserToken>(this.url, loginUser);
    }

    loginPage(redirect?: string) {
        const _redirect = redirect ? redirect : this.router.url;
        localStorage.setItem('authRedirect', _redirect);
        this.router.navigate([_redirect]);
    }

    getUserToken(): string {
        return localStorage.getItem('user_token');
    }

    getUserName(): string {
        return localStorage.getItem('user_name');
    }

    getUserId(): string {
        return localStorage.getItem('user_id');
    }

    isUserAdmin(): boolean {
        return localStorage.getItem('is-user_admin') === 'yes';
    }

    isUserManager(): boolean {
      return localStorage.getItem('is-user_manager') === 'yes';
    }

    isUserLead(): boolean {
      return localStorage.getItem('is-user_lead') === 'yes';
    }

    isLoggedin(): boolean {
        if (this.getUserToken()) {
            this.loggedSubject.next(true);
            return true;
        } else {
            this.loggedSubject.next(false);
            return false;
        }
    }

    clearUserSessionData() {
        localStorage.removeItem('user_token');
        localStorage.removeItem('user_name');
        localStorage.removeItem('user_id');
        localStorage.removeItem('is-user_admin');
        localStorage.removeItem('is-user_manager');
        localStorage.removeItem('is-user_lead');
    }
}
