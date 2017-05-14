import { Component, ViewEncapsulation, ViewChild, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

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
	private timerSubscription: Subscription;

	private routeSubscription: Subscription;

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
		private tableService: TableService,
		private route: ActivatedRoute
	) {}

	public ngOnInit(): void {
		this.currentTable="PowerSocket";
		this.getTableFromPath(this.currentTable);
		this.startTimer(5*1000);
	}

	public ngOnDestroy(): void {
		this.timerSubscription.unsubscribe();
		this.routeSubscription.unsubscribe();
	}

	public getDemo(){
		this.columns = this.columnsExample;
		this.rows = this.rowsExample;
	}

	public getTableFromPath(deviceType: string): void {
		// Récupère userId depuis l'url du browser
		this.routeSubscription = this.route.params.subscribe(params => {
			let userId = params['userId'];
			// Appel getTable
			this.getTable(userId, deviceType);
		})
	}

	public getTable(userId: number, deviceType: string): void {
		this.tableService.getTable(userId, deviceType).then(arr => {
				this.columns = arr[0];
				this.rows = arr[1];
			});
	}

	public startTimer(refreshRate: number): void {
		// Rafraichit la liste des données toutes les N millisecondes
		let timer = TimerObservable.create(0, refreshRate);
		this.timerSubscription = timer.subscribe(t => {
			this.getTableFromPath(this.currentTable);
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
