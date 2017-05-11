import { Component, OnInit } from '@angular/core';
import { TableService } from './table.service';

@Component({
  	selector: 'my-table',
	templateUrl: './table.component.html',
	styleUrls: [],
	providers: [
		TableService,
	]
})

export class TableComponent implements OnInit {
	private rows:any[] = [];
	private columns:any[] = [];

	private columnsExample:any[] = [
	    { "prop": "deviceId" },
	    { "prop": "power" },
	    { "prop": "status" },
		{ "prop": "current" }
	];

	private rowsExample:any[] = [
		{
			"deviceId":	"1",
			"power":	"12.0",
			"status":	"1.0",
			"current": "4.0",
		},
		{
			"deviceId":	"2",
			"power":	"9.0",
			"status":	"2.0",
			"current": "9.0",
		},
	];

	public constructor(
		private tableService: TableService
	) {}

	public ngOnInit(): void {
		this.getTable("PowerSocket",1);
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
}
