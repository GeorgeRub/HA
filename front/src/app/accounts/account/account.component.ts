import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AccountService} from "../../_services/account.service";
import {MatDialog} from "@angular/material/dialog";
import {SpendMoneyDialog} from "./SpendMoneyDialog";
import {SpendMoney} from "./SpendMoney";
import {SpendMoneyAdroadDialog} from "./SpendMoneyAbroadDialog";


@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

    id: string | null = null

    spendMoney: SpendMoney = new SpendMoney()

    account: any = new Account()
    accountHistory: AccountHistory[] = []

    constructor(private route: ActivatedRoute
                , private accountService: AccountService
                , public dialog: MatDialog) {
    }

    ngOnInit(): void {
        this.id = this.route.snapshot.paramMap.get('id')
        // console.log('id ', this.id)
        this.accountService.getById(this.id).subscribe(data => {
            console.log('data')
            console.log(data)
            this.account = data
        }, error => {
            console.log(error)
        })

        let startDate = new Date()
        startDate.setDate(startDate.getDate() - 5)
        let endDate = new Date()
        if (this.id != null)
            this.accountService.getHistoryByIdAndBetweenDates(this.id, startDate, endDate).subscribe(
                data => {
                    // console.log(data)
                    // @ts-ignore
                    for (let ah of data) {
                        this.accountHistory.push(ah)
                    }
                }, error => {
                    console.log(error)
                }
            )
    }

    openSpendMoneyDialog() {
        this.dialog.open(SpendMoneyDialog, {data: {spendMoney: this.spendMoney}})
    }


    openSpendMoneyAbroadDialog() {
        this.dialog.open(SpendMoneyAdroadDialog, {data: {spendMoney: this.spendMoney}})
    }


}


class Account {
    ac_id: string = ''
    accountName: string = ''
    balance: number = 0
    currency: Currency = new Currency()
    dateCreated: Date = new Date()
    dateChanged: Date | null = null
}

class Currency {
    currencyName: string = ''
}

class AccountHistory {
    balance: number = 0
    value: number = 0
    createDate: Date = new Date()
    reason: Reason = new Reason()
}

class Reason {
    name: string = ''
}
