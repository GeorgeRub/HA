import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../_services/account.service";
import {Router} from "@angular/router";
import {CurrencyService} from "../../_services/currency.service";

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

    currencyList: Currency[] = []

    constructor(private accountService: AccountService, private router: Router, private currencyService: CurrencyService) {
    }

    ngOnInit(): void {
        this.currencyService.getAll().subscribe(data => {
            // console.log('currency list', data)
            // @ts-ignore
            for(let currency of data){
                this.currencyList.push(currency)
            }
        })
    }

    onSubmit(): void {
        const {name, currency, balance} = this.form
        console.log('form', this.form)
        this.accountService.create(name, currency, balance).subscribe(
            data => {
                console.log(data)
                if (data.ac_id != undefined && data.ac_id != null && data.ac_id.length > 0) {
                    this.router.navigate(['/account/id/' + data.ac_id])
                }
            }, error => {
                console.log(error.error.message)
            }
        )
    }
}

class Currency{
    id: number = 0
    currencyName: string = ''
}