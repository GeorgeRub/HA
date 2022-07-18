import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorageService} from "./token-storage.service";
import {SpendMoney} from "../accounts/account/SpendMoney";

const AUTH_API = 'http://localhost:5000/api/account/spend/';

@Injectable({
    providedIn: 'root'
})

export class SpendMoneyService {
    httpOptions: any = null

    constructor(private http: HttpClient, private token: TokenStorageService) {
        const jwtToken = this.token.getToken();
        this.httpOptions = {
            headers: new HttpHeaders({
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            })
        };
    }

    create(spendMoney: SpendMoney): Observable<any> {
        return this.http.post(AUTH_API + 'create', {
            spendMoney
        }, this.httpOptions);
    }
}