import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SpendMoney} from "./SpendMoney";

@Component({
    selector: 'spend-money-dialog',
    templateUrl: 'spendMoneyAbroadDialog.html',
    styleUrls: ['./account.component.css']
})
export class SpendMoneyAdroadDialog {
    spendMoney: SpendMoney = new SpendMoney()

    constructor(public dialogRef: MatDialogRef<SpendMoneyAdroadDialog>,
                @Inject(MAT_DIALOG_DATA) public data: DialogData,) {
    }
    onNoClick(): void {
        this.dialogRef.close();
    }

    saveSpendMoney(){
        console.log(this.spendMoney)
        this.onNoClick()
    }
}

export interface DialogData {
    spendMoney: any;
}