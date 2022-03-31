import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {TokenStorageService} from "./_services/token-storage.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    title = 'front';

    isLoggedIn = false;
    currentUser: any = null;

    constructor(private token: TokenStorageService, private router: Router) {
    }

    ngOnInit(): void {
        console.log('user app', this.token.getUser())
        if (this.token.getToken()) {
            console.log('init user')
            this.isLoggedIn = true;
            this.currentUser = this.token.getUser();
        }
        console.log(this.currentUser)
    }

    isUser(): boolean {
        if (this.isLoggedIn && this.currentUser.roles != undefined) {
            return this.currentUser.roles.includes('ROLE_USER')
        }
        return false
    }

    isAdmin(): boolean {
        if (this.isLoggedIn && this.currentUser.roles != undefined) {
            return this.currentUser.roles.includes('ROLE_ADMIN')
        }
        return false
    }

    isModerator(): boolean {
        if (this.isLoggedIn && this.currentUser.roles != undefined) {
            return this.currentUser.roles.includes('ROLE_MODERATOR')
        }
        return false
    }

    logout(): void {
        this.token.signOut();
        this.currentUser = null;
        this.router.navigate(['/'])
        // window.location.reload();
    }

    reload() {
        this.ngOnInit()
    }
}
