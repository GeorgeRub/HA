import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SpendMoney} from "./SpendMoney";
import {CurrencyService} from "../../_services/currency.service";
import {SpendMoneyService} from "../../_services/spend.money.service";

@Component({
    selector: 'spend-money-dialog',
    templateUrl: 'spendMoneyDialog.html',
    styleUrls: ['./account.component.css']
})
export class SpendMoneyDialog {

    spendMoney: SpendMoney = new SpendMoney()
    currencyList: Currency[] = []

    isDateSet: boolean = false
    isNameSet: boolean = false
    isTotalPriceSet: boolean = false
    isCurrency: boolean = false
    isExchangePrice: boolean = false
    isTotalPriceInCurrency: boolean = false
    isCurrencyCalculation: boolean = false

    constructor(public dialogRef: MatDialogRef<SpendMoneyDialog>
        , @Inject(MAT_DIALOG_DATA) public data: DialogData
        , private currencyService: CurrencyService
        , private spendMoneyService: SpendMoneyService) {
    }

    ngOnInit(): void {
        this.currencyService.getAll().subscribe(data => {
            // @ts-ignore
            for (let currency of data) {
                this.currencyList.push(currency)
            }
        })
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    defaultRequired() {
        this.isDateSet = false
        this.isNameSet = false
        this.isTotalPriceSet = false
        this.isCurrency = false
        this.isExchangePrice = false
        this.isTotalPriceInCurrency = false
        this.isCurrencyCalculation = false
    }


    totalPriceIsChanged() {
        if (this.spendMoney.differentCurrency) {
            if (this.spendMoney.totalPrice === undefined) {
                if (this.spendMoney.exchangePrice !== undefined
                    && this.spendMoney.totalPriceInCurrency !== undefined) {
                    this.spendMoney.totalPrice = multiply(Number(this.spendMoney.exchangePrice), Number(this.spendMoney.totalPriceInCurrency))
                }
            }
        }
    }

    exchangePriceIsChanged() {
        if (this.spendMoney.exchangePrice !== undefined
            && this.spendMoney.totalPriceInCurrency !== undefined) {
            this.spendMoney.totalPrice = multiply(Number(this.spendMoney.exchangePrice), Number(this.spendMoney.totalPriceInCurrency))
        }
    }

    saveSpendMoney() {
        console.log(this.spendMoney)
        this.defaultRequired()
        let isOk = true
        if (this.spendMoney.date === undefined) {
            this.isDateSet = true
            isOk = false
        }
        if (this.spendMoney.spendName === undefined) {
            this.isNameSet = true
            isOk = false
        }
        if (this.spendMoney.totalPrice === undefined) {
            this.isTotalPriceSet = true
            isOk = false
        }
        if (this.spendMoney.differentCurrency) {
            if (this.spendMoney.currency === undefined) {
                this.isCurrency = true
                isOk = false
            }
            if (this.spendMoney.exchangePrice === undefined) {
                this.isExchangePrice = true
                isOk = false
            }
            if (this.spendMoney.totalPriceInCurrency === undefined) {
                this.isTotalPriceInCurrency = true
                isOk = false
            }
            if (this.spendMoney.exchangePrice !== undefined
                && this.spendMoney.totalPriceInCurrency !== undefined
                && this.spendMoney.totalPrice !== undefined) {
                let totalCurrency = multiply(Number(this.spendMoney.exchangePrice), Number(this.spendMoney.totalPriceInCurrency))
                if (totalCurrency != this.spendMoney.totalPrice) {
                    this.isCurrencyCalculation = true
                    isOk = false
                }
            }
        }
        if (isOk) {
            this.spendMoneyService.create(this.spendMoney).subscribe(
                data => {
                    console.log(data)
                    this.onNoClick()
                }, error => {
                    console.log(error)
                }
            )

        }

    }
}

export interface DialogData {
    spendMoney: any;
}

class Currency {
    currencyName: string = ''
}

function multiply(f: number, l: number): number {
    return Math.round(f * l * 1e12) / 1e12
}