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
	private editMenuBackup: any[] = [];

	public constructor (
		private editingMenuService: EditingMenuService
	) {}

	public ngOnInit(): void {
		console.log(this.tableColumns);
		console.log(this.selectedRow);
		this.getEditingMenu(this.selectedRow[0].DeviceId);
	}

	public edit(): void {
		console.log(this.editMenu);
		for (let i=0; i<this.editMenu.length; i++) {
			let curr: any = this.editMenu[i];
			let backup: any = this.editMenuBackup[i];
			console.log(curr['value']);
			if (curr['value'] !== backup['value']) {
				this.changeState(curr['name'], curr['value']);
			}
		}
	}

	public changeState(name: string, value: string){

	}

	private getEditingMenu(deviceId: string): void {
		this.editingMenuService.getEditingMenu(deviceId).then(menu => {
			this.editMenu = menu;
			this.editMenuBackup = menu;
		});
	}
}
