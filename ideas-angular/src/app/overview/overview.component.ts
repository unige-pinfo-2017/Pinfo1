import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Subscription } from "rxjs";
import { TimerObservable } from "rxjs/observable/TimerObservable";

import { SidebarComponent } from './sidebar/sidebar.component';
import { TopbarComponent } from './topbar/topbar.component';
import { MiddlescreenComponent } from './middlescreen/middlescreen.component';

import { OverviewService } from './overview.service';

@Component({
	selector: 'overview',
	templateUrl: './overview.component.html',
	styleUrls: ['./overview.component.css'],
	providers: [ OverviewService ],
})

export class OverviewComponent implements OnInit, OnDestroy {
	@ViewChild(MiddlescreenComponent) middlescreenComponent: MiddlescreenComponent;

	private currentId: number; // L'id de l'utilisateur dont on génère la table, n'est pas forcément l'id de l'utilisateur authentifié
	private refreshRate: number = 5*1000; // refreshRate en milliseconde
	private timerSubscription: Subscription;
	private routeSubscripton: Subscription;
	private liveData: any[];
	private hiddenData: any[];

	public constructor(
		private overviewService: OverviewService,
		private router: Router,
		private route: ActivatedRoute
	){}

	public ngOnInit(): void {
		this.initCurrentId();
		this.getLiveData(this.currentId);
		this.getHiddenData(this.currentId);
		this.startTimer(this.refreshRate);
	}

	public ngOnDestroy(): void {
		if (this.timerSubscription != null) this.timerSubscription.unsubscribe();
		if (this.routeSubscripton != null) this.routeSubscripton.unsubscribe();
	}

	public startTimer(refreshRate: number): void {
		// Refresh the live data every N milliseconds
		let timer = TimerObservable.create(1000, refreshRate); // Create a timer that calls the method every N milliseconds
		this.timerSubscription = timer.subscribe(t => {
			// At each timer tick, we refresh the data
			this.getLiveData(this.currentId);
		})
	}

	private initCurrentId(): void {
		this.currentId = this.getCurrentId();
	}

	private getCurrentId(): number {
		let currentId: number;
		this.route.params.subscribe(params => {
			currentId = params['userId'];
		});
		if (currentId == undefined) {
			return +sessionStorage.getItem('id');
		}
		//this.routeSubscripton.unsubscribe();
		return currentId;
	}

	public getLiveData(userId: number): void {
		this.overviewService.getLiveData(userId).then(liveData => this.liveData = liveData);
	}

	public getHiddenData(userId: number): void {
		this.overviewService.getHiddenData(userId).then(hiddenData => this.hiddenData = hiddenData);
	}

	public addLiveData(name: string): void {
		//console.log(name);
		let sessionId: number = Number(sessionStorage.getItem('id'));
		if (sessionId === this.currentId) {
			// We can change preference only if we are on our own overview, i.e. A manager cannot change the preference of one of its subordinate.
			this.overviewService.addLiveData(this.currentId, name)
			.then(res => {
				//console.log(res);
				this.getLiveData(this.currentId);
				this.getHiddenData(this.currentId);
			})
		}
	}

	public changeLiveData(name: string): void {
		this.middlescreenComponent.getChartDataShort(name.toLowerCase(), "day");
	}

	public removeLiveData(name: string): void {
		//console.log(name);
		let sessionId: number = Number(sessionStorage.getItem('id'));
		if (sessionId === this.currentId) {
			// We can change preference only if we are on our own overview, i.e. A manager cannot change the preference of one of its subordinate.
			this.overviewService.removeLiveData(this.currentId, name)
			.then(res => {
				//console.log(res);
				this.getLiveData(this.currentId);
				this.getHiddenData(this.currentId);
			})
		}
	}
}
