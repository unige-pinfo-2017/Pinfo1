import { Component, OnInit } from '@angular/core';

import { SidebarComponent } from './sidebar/sidebar.component';
import { TopbarComponent } from './topbar/topbar.component';
import { MiddlescreenComponent } from './middlescreen/middlescreen.component';

import { OverviewService } from './overview.service';

// Constante à remplacer
const DATAH: any[] = [
	{name: 'Conso', value: 573, unit: 'kW/h'},
	{name: 'Temperature', value: 25, unit: '°C'},
	{name: 'elec', value: 450, unit: 'W'},
	{name: 'blib blab', value: 24874, unit: 'sec'}
];

@Component({
	selector: 'overview',
	templateUrl: './overview.component.html',
	styleUrls: ['./overview.component.css'],
	providers: [ OverviewService ],
})

export class OverviewComponent implements OnInit {
	private liveData: any[];
	private hiddenLiveData: any[];

	public constructor(private overviewService: OverviewService){}

	public ngOnInit():void {
		this.getLiveData(1);
		this.hiddenLiveData = DATAH;
		console.log("ngOnInit success");
	}

	public getLiveData(userId: number): void {
		this.overviewService.getLiveData(userId).then(liveData => this.liveData = liveData);
	}
}
