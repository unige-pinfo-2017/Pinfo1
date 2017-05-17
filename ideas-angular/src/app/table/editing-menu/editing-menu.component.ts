import { Component, Input } from '@angular/core';


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
	})

export class EditingMenuComponent {
	param = PARAM;
	a: { state: string; value: string; } = { state: "Status", value: "ON" };

}
