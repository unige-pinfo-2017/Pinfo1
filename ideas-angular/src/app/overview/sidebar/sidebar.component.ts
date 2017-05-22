import { Component, Output, EventEmitter, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from "rxjs";


@Component({
	selector: 'sidebar',
	templateUrl: './sidebar.component.html',
	styleUrls: ['./sidebar.component.css'],
})

export class SidebarComponent implements OnInit {
	private role: string;

	private routeSubscripton: Subscription;

	public constructor(
		private router: Router,
		private route: ActivatedRoute
	){}


	public navigateToTable(type: string): void {
		this.routeSubscripton = this.route.params.subscribe(params => {
		let userId = params['userId'];
		this.router.navigate(['/table', type]);
		})
	}
	public ngOnInit(): void {
		this.role = sessionStorage.getItem('role');
	}
}
