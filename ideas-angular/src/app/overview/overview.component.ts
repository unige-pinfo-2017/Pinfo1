import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from "rxjs";
import { TimerObservable } from "rxjs/observable/TimerObservable";

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

export class OverviewComponent implements OnInit, OnDestroy {
	private refreshRate: number = 20*1000; // refreshRate en milliseconde
	private subscription: Subscription;
	private liveData: any[];
	private hiddenLiveData: any[];

	public constructor(private overviewService: OverviewService){}

	public ngOnInit():void {
		this.getLiveData(1);
		this.hiddenLiveData = DATAH;
		this.startTimer(this.refreshRate);
	}

	public ngOnDestroy() {
		this.subscription.unsubscribe();
	}

	public startTimer(refreshRate: number): void {
		// Rafraichit la liste des données live toutes les N millisecondes
		// Ne bloque pas l'exécution
		let timer = TimerObservable.create(0, refreshRate); // Créer un timer qui s'enclenche toutes les N secondes
		this.subscription = timer.subscribe(t => {
			// A chaque tick du timer, on rafraichit les donnees live
			this.getLiveData(1);
			//console.log("Refreshing live data");
		})
	}

	public getLiveData(userId: number): void {
		this.overviewService.getLiveData(userId).then(liveData => this.liveData = liveData);
	}
}
