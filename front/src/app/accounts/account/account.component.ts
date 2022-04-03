import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {AccountService} from "../../_services/account.service";

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

    id: string | null = null

    account: any = new Account()
    accountHistory: AccountHistory[] = []

    constructor(private route: ActivatedRoute, private accountService: AccountService) {
    }

    ngOnInit(): void {
        this.id = this.route.snapshot.paramMap.get('id')
        console.log('id ', this.id)
        this.accountService.getById(this.id).subscribe(data => {
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
                    console.log(data)
                    // @ts-ignore
                    for (let ah of data) {
                        this.accountHistory.push(ah)
                    }
                    console.log('accountHistory', this.accountHistory);
                }, error => {
                    console.log(error.error.message)
                }
            )
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
    name: string = ''
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