import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ReactiveFormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";
import {UserComponent} from "./user/user.component";
import {IndexComponent} from './index/index.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {Ng2GoogleChartsModule} from "ng2-google-charts";

const routes: Routes = [
    {path: '', component: IndexComponent},
    {path: 'user', component: UserComponent},
    {path: 'login', component: LoginComponent},
    {path: 'logout', component: LogoutComponent}
];

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        IndexComponent
    ],
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        RouterModule.forRoot(routes),
        BrowserAnimationsModule,
        Ng2GoogleChartsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})

export class AppModule {
}