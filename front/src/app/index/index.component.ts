import {Component, OnInit} from '@angular/core';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {
    GoogleChartInterface,
    GoogleChartsControlInterface,
    GoogleChartsDashboardInterface,
    GoogleChartType
} from 'ng2-google-charts';

@Component({
    selector: 'app-index',
    templateUrl: './index.component.html',
    styleUrls: ['./index.component.css']
})
export class IndexComponent  {
    lineChart: GoogleChartInterface = {
        chartType: GoogleChartType.LineChart,
        dataTable: [
            ['Student',  'Heorhii'],
            ['A', 80],
            ['B', 90],
            ['C', 88]
        ],
        options: { 'title': 'Student Heorhii' },
    };

    public slider: GoogleChartsControlInterface = {
        controlType: 'NumberRangeFilter',
        options: {
            filterColumnIndex: 2,
            ui: {
                labelStacking: 'vertical',
                label: 'Age Filter:'
            }
        }
    };

    public categoryPicker: GoogleChartsControlInterface = {
        controlType: 'CategoryFilter',
        options: {
            filterColumnIndex: 1,
            ui: {
                labelStacking: 'vertical',
                label: 'Gender Selection:',
                allowTyping: false,
                allowMultiple: false
            }
        }
    };

    public dashboardPieChart: GoogleChartInterface = {
        chartType: 'PieChart',
        options: {
            width: 250,
            height: 250,
            legend: 'none',
            chartArea: {left: 15, top: 15, right: 0, bottom: 0},
            pieSliceText: 'label'
        },
        view: {columns: [0, 3]}
    };

    public dashboardTable: GoogleChartInterface = {
        chartType: 'Table',
        options: {
            alternatingRowStyle: true,
            showRowNumber : true,
            allowHtml: true,
        },
    };

    public dashboard: GoogleChartsDashboardInterface = {
        dataTable: [
            ['Name', 'Gender', 'Age', 'Donuts eaten'],
            ['Michael' , 'Male', 12, 5],
            ['Elisa', 'Female', 20, 7],
            ['Robert', 'Male', 7, 3],
            ['John', 'Male', 54, 2],
            ['Jessica', 'Female', 22, 6],
            ['Aaron', 'Male', 3, 1],
            ['Margareth', 'Female', 42, 8],
            ['Miranda', 'Female', 33, 6]
        ],
        formatters: [
            {
                columns: [3],
                type: 'ArrowFormat',
                options: {
                    base: 5,
                },
            },
        ],
        bind: [
            [
                [this.slider, this.categoryPicker],
                [this.dashboardPieChart, this.dashboardTable]
            ]
        ],
    };
}
