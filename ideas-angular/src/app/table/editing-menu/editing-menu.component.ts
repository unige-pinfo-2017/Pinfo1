import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { EditingMenuService } from './editing-menu.service';
import { Response } from '@angular/http';

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
	@Input() selectedDeviceId: string;
	@Output() edited:EventEmitter<any> = new EventEmitter();
	private editMenu: any[] = [];

	public constructor (
		private editingMenuService: EditingMenuService
	) {}

	public ngOnInit(): void {
		this.getEditingMenu(this.selectedRow[0].DeviceId);
	}

	/*public edit(): void {
		let promises:Promise<string>[] = [];
		let p: Promise<string>;
		for (let i=0; i<this.editMenu.length; i++) {
			let curr: any = this.editMenu[i];
			let name:string = curr['name'];
			let value:number = curr['value'];
			promises.push(this.checkAndChange(name, value));
		}
		Promise.all(promises).then(res => this.edited.emit());
	}*/

	public checkAndChange(name:string, value:number):void {
		if (name === "State") {
			if (value !=0 && value != 1) return;
		} else if (name === "Hue") {
			if (value < 0 || value > 360) return;
		} else if (name === "Saturation") {
			if (value < 0 || value > 1) return;
		} else if (name === "Kelvin") {
			if (value < 5000 || value > 9999) return;
		} else {
			return;
		}
		this.changeState(name,value).then(res => this.edited.emit());
	}

	public changeState(name: string, value: number): Promise<string> {
		return this.editingMenuService.changeState(this.selectedDeviceId, name, String(value));
	}

	private getEditingMenu(deviceId: string): void {
		console.log("getEditMenu called");
		this.editingMenuService.getEditingMenu(deviceId).then(menu => {
			this.editMenu = menu;
		});
	}
}
