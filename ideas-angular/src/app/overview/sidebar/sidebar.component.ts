import { Component, Output, EventEmitter, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from "rxjs";


@Component({
	selector: 'sidebar',
	templateUrl: './sidebar.component.html',
	styleUrls: ['./sidebar.component.css'],
})

export class SidebarComponent {

	private routeSubscripton: Subscription;

	public constructor(
		private router: Router,
		private route: ActivatedRoute
	){}


	public navigateToTable(): void {
		this.routeSubscripton = this.route.params.subscribe(params => {
		let userId = params['userId'];
		this.router.navigate([`/table`]);
		})
	}
}
