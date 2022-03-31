import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../_services/token-storage.service";

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
    currentUser: any;

    constructor(private token: TokenStorageService) {
    }

    ngOnInit(): void {
        if (this.token.getUser().username != undefined)
            this.currentUser = this.token.getUser();
        console.log(this.currentUser)
    }

}
