import {Component, OnInit} from '@angular/core';
import {AccountService} from "../_services/account.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-accounts',
    templateUrl: './accounts.component.html',
    styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {

    accounts: Account[] = []

    constructor(private accountService: AccountService, private router: Router) {
    }

    ngOnInit(): void {
        this.accountService.allAccounts().subscribe(data => {
            console.log('data', data)
            this.accounts = data as Account[]
            console.log('accounts', this.accounts)
        }, error => {
            console.log('error', error)
        })
    }

    openAccount(id: string) {
        console.log('id', id)
        this.router.navigate(['/account/id/' + id])
    }

}

class Account {
    ac_id: string = ''
    accountName: string = ''
    balance: number = 0
    dateCreated: Date = new Date()
    currency: Currency = new Currency()
}

class Currency {
    name: string = ''
}