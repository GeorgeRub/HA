import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../_services/account.service";

@Component({
    selector: 'app-crerate',
    templateUrl: './crerate.component.html',
    styleUrls: ['./crerate.component.css']
})
export class CrerateComponent implements OnInit {
    form: any = {
        name: null,
        currency: null,
        balance: 0.00
    }

    currencyList = ["UAH","USD","CAD"]

    constructor(private accountService: AccountService) {
    }

    ngOnInit(): void {
    }

    onSubmit(): void {
        const{name, currency, balance} = this.form
        console.log('form',this.form)
        this.accountService.create(name,currency,balance).subscribe(
            data =>{
                console.log(data)
            }, error => {
                console.log(error.error.message)
            }
        )
    }
}
