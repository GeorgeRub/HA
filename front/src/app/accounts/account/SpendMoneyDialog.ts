import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SpendMoney} from "./SpendMoney";
import {CurrencyService} from "../../_services/currency.service";

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
        , private currencyService: CurrencyService) {
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
                    this.spendMoney.totalPrice = this.spendMoney.exchangePrice * this.spendMoney.totalPriceInCurrency
                }
            }
        }
    }

    // totalPriceInCurrencyIsChanged(){
    //     if (this.spendMoney.totalPrice === undefined) {
    //         if (this.spendMoney.exchangePrice !== undefined
    //             && this.spendMoney.totalPriceInCurrency !== undefined) {
    //             this.spendMoney.totalPrice = this.spendMoney.exchangePrice * this.spendMoney.totalPriceInCurrency
    //         }
    //     }
    // }
    exchangePriceIsChanged(){
        // if (this.spendMoney.totalPrice === undefined) {
            if (this.spendMoney.exchangePrice !== undefined
                && this.spendMoney.totalPriceInCurrency !== undefined) {
                this.spendMoney.totalPrice = Math.round(Number(this.spendMoney.exchangePrice) * Number(this.spendMoney.totalPriceInCurrency)* 1e12) / 1e12
            }
        // }
    }

    saveSpendMoney() {
        console.log(this.spendMoney)
        this.defaultRequired()
        let isOk = true
        if (this.spendMoney.date === undefined) {
            this.isDateSet = true
            isOk = false
        }
        if (this.spendMoney.name === undefined) {
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
                let totalCurrency = this.spendMoney.exchangePrice * this.spendMoney.totalPriceInCurrency
                if (totalCurrency != this.spendMoney.totalPrice) {
                    this.isCurrencyCalculation = true
                    isOk = false
                }
            }
        }
        if (isOk)
            this.onNoClick()
    }
}

export interface DialogData {
    spendMoney: any;
}

class Currency {
    name: string = ''
}