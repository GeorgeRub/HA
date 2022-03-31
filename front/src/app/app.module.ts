import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {UserComponent} from "./user/user.component";
import {IndexComponent} from './index/index.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Ng2GoogleChartsModule} from "ng2-google-charts";
import { RegisterComponent } from './register/register.component';
import {HttpClientModule} from "@angular/common/http";
import { AccountComponent } from './accounts/account/account.component';
import { AccountsComponent } from './accounts/accounts.component';
import { CrerateComponent } from './accounts/crerate/crerate.component';

const routes: Routes = [
    {path: '', component: IndexComponent},
    {path: 'user', component: UserComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'account', component: AccountsComponent},
    {path: 'account/create', component: CrerateComponent},
    {path: 'account/id/:id', component: AccountComponent},
    {path: 'logout', component: LogoutComponent}
];

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        IndexComponent,
        RegisterComponent,
        AccountComponent,
        AccountsComponent,
        CrerateComponent
    ],
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        RouterModule.forRoot(routes),
        BrowserAnimationsModule,
        Ng2GoogleChartsModule,
        FormsModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})

export class AppModule {
}