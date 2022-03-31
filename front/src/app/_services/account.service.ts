import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {TokenStorageService} from "./token-storage.service";
const AUTH_API = 'http://localhost:8080/api/account/';
// const httpOptions = {
//     headers: new HttpHeaders({ 'Content-Type': 'application/json' })
// };
@Injectable({
    providedIn: 'root'
})
export class AccountService {
    httpOptions: any = null
    constructor(private http: HttpClient, private token: TokenStorageService) {
        const tok = this.token.getToken();
        this.httpOptions = {
            headers: new HttpHeaders({ 'Content-Type': 'application/json',
                'Authorization': `Bearer ${tok}` })
        };
    }
    // login(username: string, password: string): Observable<any> {
    //     return this.http.post(AUTH_API + 'signin', {
    //         username,
    //         password
    //     }, httpOptions);
    // }
    create(name: string, currency: string, balance: number): Observable<any> {
        return this.http.post(AUTH_API + 'create', {
            name,
            currency,
            balance
        }, this.httpOptions);
    }
}