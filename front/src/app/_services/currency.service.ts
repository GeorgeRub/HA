import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorageService} from "./token-storage.service";

const AUTH_API = 'http://localhost:5000/api/service/currency/';

@Injectable({
    providedIn: 'root'
})
export class CurrencyService {
    httpOptions: any = null

    constructor(private http: HttpClient, private token: TokenStorageService) {
        const tok = this.token.getToken();
        this.httpOptions = {
            headers: new HttpHeaders({
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${tok}`
            })
        };
    }

    // allAccounts(): Observable<any> {
    //     console.log('all accounts')
    //     return this.http.get(AUTH_API + 'all', this.httpOptions);
    // }
    //
    // create(name: string, currency: string, balance: number): Observable<any> {
    //     return this.http.post(AUTH_API + 'create', {
    //         name,
    //         currency,
    //         balance
    //     }, this.httpOptions);
    // }

    getAll() {
        return this.http.get(AUTH_API + 'all', this.httpOptions);
    }
}