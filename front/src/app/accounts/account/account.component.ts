import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

    id: string | null = null

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.id = this.route.snapshot.paramMap.get('id')
        console.log('id ', this.id)
    }

}
