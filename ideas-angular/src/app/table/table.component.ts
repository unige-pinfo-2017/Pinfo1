<<<<<<< Updated upstream
import { Component, OnInit } from '@angular/core';
=======
import { Location, Component, ViewEncapsulation, ViewChild } from '@angular/core';
>>>>>>> Stashed changes
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
	]
	encapsulation: ViewEncapsulation.None
})

<<<<<<< Updated upstream
export class TableComponent implements OnInit {
=======
export class TableComponent {

	@ViewChild('myTable') table: any;

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
	public ngOnInit(): void {
		this.getTable("PowerSocket",1);
	}
=======

	onPage(event) {
	    clearTimeout(this.timeout);
	    this.timeout = setTimeout(() => {
	      console.log('paged!', event);
	    }, 100);
  	}
>>>>>>> Stashed changes

	public getDemo(){
		this.columns = this.columnsExample;
		this.rows = this.rowsExample;
	}

	public getTable(deviceType: string, userid: number): void {
		this.tableService.getTable(deviceType, userid)
		.then(arr => {
			this.columns = arr[0];
			this.rows = arr[1];
		})
	}

	public getDeviceTable(deviceType: string): void {
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
	}
	onSelect(event: any) {
    	console.log('Event: select', event, this.selected);
  	}

  	onActivate(event: any) {
    	console.log('Event: activate', event);
	}
	toggleExpandRow(row) {
    	console.log('Toggled Expand Row!', row);
    	this.table.rowDetail.toggleExpandRow(row);
  	}

  	onDetailToggle(event) {
    	console.log('Detail Toggled', event);
	}
}
