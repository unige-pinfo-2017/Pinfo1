import { Component } from '@angular/core';

@Component({
	selector: 'topbar',
	templateUrl: './topbar.component.html',
	styleUrls: ['./topbar.component.css'],
})

export class TopbarComponent {
	datas = DATA;
}
export class Data {
	name: string;
	valeur: number;
	unite: string;
}

const DATA: Data[] = [
	{name: 'Conso', valeur: 573, unite: 'kW/h'},
	{name: 'Temperature', valeur: 25, unite: '°C'},
	{name: 'elec', valeur: 450, unite: 'W'},
	{name: 'blib blab', valeur: 24874, unite: 'sec'},

];
