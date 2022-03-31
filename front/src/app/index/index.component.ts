import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";
import {AppComponent} from "../app.component";

@Component({
    selector: 'app-index',
    templateUrl: './index.component.html',
    styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {
    title = 'front';
    isLoggedIn = false;
    currentUser: any = null;

    constructor(private token: TokenStorageService, private router: Router, public appComponent: AppComponent) {
    }

    ngOnInit(): void {
        console.log('user', this.token.getUser())
        if (this.token.getToken()) {
            this.isLoggedIn = true;
            this.currentUser = this.token.getUser().roles;
        }
        console.log(this.currentUser)
    }
}
