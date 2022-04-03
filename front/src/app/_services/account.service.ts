import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TokenStorageService} from "./token-storage.service";

const AUTH_API = 'http://localhost:8080/api/account/';

@Injectable({
    providedIn: 'root'
})
export class AccountService {
    httpOptions: any = null

    constructor(private http: HttpClient, private token: TokenStorageService) {
        const tok = this.token.getToken();
        this.httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${tok}`
            })
        };
    }

    allAccounts(): Observable<any> {
        console.log('all accounts')
        return this.http.get(AUTH_API + 'all', this.httpOptions);
    }

    getHistoryByIdAndBetweenDates(id: string, startDate: Date, endDate: Date) {
        return this.http.get(AUTH_API + 'history/id/' + id + '/startDate/' + startDate + '/endDate/' + endDate, this.httpOptions);
    }

    create(name: string, currency: string, balance: number): Observable<any> {
        return this.http.post(AUTH_API + 'create', {
            name,
            currency,
            balance
        }, this.httpOptions);
    }

    getById(id: string | null) {
        return this.http.get(AUTH_API + 'id/' + id, this.httpOptions);
    }
}