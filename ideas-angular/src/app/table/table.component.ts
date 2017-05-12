import { Component, ViewEncapsulation, ViewChild, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from "rxjs";
import { TimerObservable } from "rxjs/observable/TimerObservable";

import { TableService } from './table.service';

//@import '/node_modules/@swimlane/ngx-datatable/release/datatable.css';
//@import '/node_modules/@swimlane/ngx-datatable/release/themes/material.css';
//@import '/node_modules/@swimlane/ngx-datatable/release/assets/icons.css';

@Component({
  	selector: 'my-table',
	templateUrl: './table.component.html',
	styleUrls: [
	'./material.scss',
	'/node_modules/@swimlane/ngx-datatable/release/themes/material.css',
	//'./material.css'
	'./datatable.css',
	//'./style.css'
	'./table.component.css',
	],
	providers: [
		TableService,
	],
	encapsulation: ViewEncapsulation.None
})

export class TableComponent implements OnInit, OnDestroy {

	@ViewChild('myTable') table: any;

	private refreshRate: number = 20*1000; // Fréquence de rafraichissement en milliseconde
	private subscription: Subscription;

	private currentTable: string;
	private rows:any[] = [];
	private columns:any[] = [];
	expanded: any = {};
	selected: any[] = [];
	timeout: any;

	private columnsExample:any[] = [
	    { "prop": "deviceId" },
	    { "prop": "power" },
	    { "prop": "status" },
		{ "prop": "current" },
		{ "prop": "state"}
	];

	private rowsExample:any[] = [

		{
			"deviceId":	"1",
			"power":	"12.0",
			"status":	"1.0",
			"current": 	"4.0",
			"state": 	"<b><font color='green'>ON</font></b>"
		},
		{
			"deviceId":	"2",
			"power":	"9.0",
			"status":	"2.0",
			"current": 	"9.0",
			"state": 	"<b><font color='red'>OFF</font></b>"
		},
	];

	public constructor(
		private tableService: TableService
	) {}

	public ngOnInit(): void {
		this.currentTable="PowerSocket";
		this.getTable(this.currentTable, 1);
		this.startTimer(5*1000);
	}

	public ngOnDestroy(): void {
		this.subscription.unsubscribe();
	}

	public getDemo(){
		this.columns = this.columnsExample;
		this.rows = this.rowsExample;
	}

	public getTable(deviceType: string, userid: number): void {
		this.tableService.getTable(deviceType, userid)
		.then(arr => {
			this.columns = arr[0];
			this.rows = arr[1];
		});
		this.currentTable = deviceType;
	}

	public startTimer(refreshRate: number): void {
		// Rafraichit la liste des données toutes les N millisecondes
		let timer = TimerObservable.create(0, refreshRate);
		this.subscription = timer.subscribe(t => {
			this.getTable(this.currentTable, 1);
			console.log("table refreshing");
		})
	}

	/*public getDeviceTable(deviceType: string): void {
		this.getDeviceColumns(deviceType);
		this.getDeviceData(deviceType);
	}

	public getDeviceColumns(deviceType: string): Promise<any> {
		return this.tableService.getDeviceColumns(deviceType).then(
			columns => this.columns = columns
		);
	}

	public getDeviceData(deviceType: string): Promise<any> {
		return this.tableService.getDeviceData(deviceType).then(
			data => this.rows = data
		);
	}*/

	onSelect(event: any) {
    	console.log('Event: select', event, this.selected);
  	}

  	onActivate(event: any) {
    	console.log('Event: activate', event);
	}
	toggleExpandRow(row: any[]) {
    	console.log('Toggled Expand Row!', row);
    	this.table.rowDetail.toggleExpandRow(row);
  	}

  	onDetailToggle(event: any) {
    	console.log('Detail Toggled', event);
	}
}
