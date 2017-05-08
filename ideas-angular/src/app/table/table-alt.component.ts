import { Component } from '@angular/core';
import { TableService } from './table.service';

@Component({
  	selector: 'basic-auto-demo',
	templateUrl: './table-alt.component.html',
	styleUrls: [],
	providers: [
		TableService,
	]
})

export class TableAltComponent {
	private rows:any[] = [];
	private columns:any[] = [];

	public constructor(
		private tableService: TableService
	) {}

	public getDeviceTable(deviceType: string): void {
		/*let promises: Promise<any>[] = [];
		promises.push(this.getDeviceColumns(deviceType));
		promises.push(this.getDeviceData(deviceType));
		Promise.all(promises).then();*/
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
