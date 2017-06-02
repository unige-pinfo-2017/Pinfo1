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
	private show: boolean = false;
	private i: number = 1;
	private showNotif: boolean = false;
	private param: any[] = [
		{id: 1, name: "powerSocket", batterie: 10, owner:'jacques'},
		{id: 2, name: "Beacon", batterie: 99, owner:'V'}
		];

	private routeSubscripton: Subscription;

	public constructor(
		private router: Router,
		private route: ActivatedRoute
	){}

	public notifClick(){
      //this.i = this.i-1;
			this.showNotif = !this.showNotif;
  }

	public navigateToTable(type: string): void {
		this.routeSubscripton = this.route.params.subscribe(params => {
		let userId = params['userId'];
		this.router.navigate(['/table', type]);
		})
	}
	public ngOnInit(): void {
		this.role = sessionStorage.getItem('role');
		//console.log(sessionStorage.getItem('role'));
		this.hideForBasicUser();
	}

	public hideForBasicUser(): void {
		//console.log(sessionStorage.getItem('role'));
		if (sessionStorage.getItem('role') === 'Basic') {
			this.show = !this.show;
		}
	}

	public logout(): void {
		sessionStorage.clear();
		this.router.navigate(['/login']);
	}
}
