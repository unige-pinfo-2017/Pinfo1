import { Component, Input, OnInit } from '@angular/core';
import { EditingMenuService } from './editing-menu.service';

const PARAM: any[] = [
	{name: 'Brightness', value: 0, unit: 'kW/h'},
	{name: 'Temperature', value: 25, unit: '°C'},
	{name: 'elec', value: 450, unit: 'W'},
	{name: 'blib blab', value: 24874, unit: 'sec'}
	];

	@Component({
		selector: 'editing-menu',
		templateUrl: './editing-menu.component.html',
		styleUrls: ['./editing-menu.component.css'],
		providers: [ EditingMenuService ]
	})


export class EditingMenuComponent implements OnInit {
	@Input() tableColumns: any[];
	@Input() selectedRow: any[];
	@Input() selectedDeviceType: string;
	private editMenu: any[] = [];

	public constructor (
		private editingMenuService: EditingMenuService
	) {}

	public ngOnInit(): void {
		console.log(this.tableColumns);
		console.log(this.selectedRow);
		this.getEditingMenu(this.selectedRow[0].DeviceId);
	}

	private buildEditMenu(): void {

	}

	private getEditingMenu(deviceId: string): void {
		this.editingMenuService.getEditingMenu(deviceId).then(menu =>
			this.editMenu = menu
		);
	}
}
