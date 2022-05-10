import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {SpendMoney} from "./SpendMoney";

@Component({
    selector: 'spend-money-dialog',
    templateUrl: 'spendMoneyDialog.html',
    styleUrls: ['./account.component.css']
})
export class SpendMoneyDialog {
    spendMoney: SpendMoney = new SpendMoney()

    constructor(public dialogRef: MatDialogRef<SpendMoneyDialog>,
                @Inject(MAT_DIALOG_DATA) public data: DialogData,) {
    }
    onNoClick(): void {
        this.dialogRef.close();
    }
}

export interface DialogData {
    spendMoney: any;
}