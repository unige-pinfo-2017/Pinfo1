import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { User } from './user';
import { UserService } from './user.service';

const USER: User = { id: 11, username: 'ideas', password: 'ideaspw'};

@Component({
	selector: 'my-user',
	templateUrl: './user.component.html',
	styleUrls: ['./user.component.css'],
	providers: [
		UserService,
	]
})

export class UserComponent implements OnInit {
	user: User;
	sub: any;

	constructor(
		private router: Router,
		private userService: UserService,
		private route: ActivatedRoute) {}

	ngOnInit(): void {
		this.sub = this.route.params.subscribe(params => {
			let username = params['username'];
			this.userService
				.getUserByUsername(username)
				.subscribe(u => this.user = u);
		})
	}

	ngOnDestroy() { // Procédure qui s'exécute lorsque le composant est détruit
		this.sub.unsubcribe();
	}

	/**getUser(id: number): void {
		this.userService.getUser(id).subscribe(u => this.user = u);
	}*/
}
