import { Component, OnInit} from '@angular/core';
import { TopbarService } from './topbar.service';

@Component({
	selector: 'topbar',
	templateUrl: './topbar.component.html',
	styleUrls: ['./topbar.component.css'],
	providers: [ TopbarService ]
})

export class TopbarComponent implements OnInit {
	datas = DATA;
	datash = DATAH;

	public constructor(
		private topbarService: TopbarService
	) {}

	public ngOnInit(){
		this.getLiveData(1); // Pour le moment on peut mettre n'importe quel value, elle ne va pas influencé la requête
	}

	public getLiveData(userId: number){
		this.topbarService.getLiveData(userId).then(data => this.datas = data);
	}
}
export class Data {
	name: string;
	value: number;
	unit: string;
}

export class DataHidden {
	name: string;
	value: number;
	unit: string;
}

const DATA: Data[] = [
	{name: 'Conso', value: 573, unit: 'kW/h'},
	{name: 'Temperature', value: 25, unit: '°C'},
	{name: 'elec', value: 450, unit: 'W'},
	{name: 'blib blab', value: 24874, unit: 'sec'},
	{name: 'power', value: 45.5, unit: 'Jl'}
];

const DATAH: DataHidden[] = [
	{name: 'Conso', value: 573, unit: 'kW/h'},
	{name: 'Temperature', value: 25, unit: '°C'},
	{name: 'elec', value: 450, unit: 'W'},
	{name: 'blib blab', value: 24874, unit: 'sec'}
];
