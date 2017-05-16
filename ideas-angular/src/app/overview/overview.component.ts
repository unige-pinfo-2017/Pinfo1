import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

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
	private refreshRate: number = 5*1000; // refreshRate en milliseconde
	private timerSubscription: Subscription;
	private routeSubscripton: Subscription;
	private liveData: any[];
	private hiddenLiveData: any[];

	public constructor(
		private overviewService: OverviewService,
		private router: Router,
		private route: ActivatedRoute
	){}

	public ngOnInit(): void {
		this.getLiveDataFromPath();
		this.hiddenLiveData = DATAH;
		this.startTimer(this.refreshRate);
	}

	public ngOnDestroy(): void {
		this.timerSubscription.unsubscribe();
		this.routeSubscripton.unsubscribe();
	}

	public startTimer(refreshRate: number): void {
		// Rafraichit la liste des données live toutes les N millisecondes
		// Ne bloque pas l'exécution
		let timer = TimerObservable.create(0, refreshRate); // Créer un timer qui s'enclenche toutes les N secondes
		this.timerSubscription = timer.subscribe(t => {
			// A chaque tick du timer, on rafraichit les donnees live
			this.getLiveDataFromPath();
			//console.log("Refreshing live data");
		})
	}

	public getLiveDataFromPath(): void {
		// Récupère userId depuis l'url du browser
		this.routeSubscripton = this.route.params.subscribe(params => {
			let userId = params['userId'];
			// Appel getLiveData
			this.getLiveData(userId);
		})
	}

	public getLiveData(userId: number): void {
		this.overviewService.getLiveData(userId).then(liveData => this.liveData = liveData);
	}

	public navigateToTable(): void {
		this.routeSubscripton = this.route.params.subscribe(params => {
			let userId = params['userId'];
			this.router.navigateByUrl(`/table/${userId}`);
		})
	}
}
