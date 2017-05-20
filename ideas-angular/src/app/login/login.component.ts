import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from './login.service';

@Component({
	selector: 'login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css'],
	providers: [
		LoginService,
	]
})

export class LoginComponent {
	title = 'I.D.E.A.S';
	private username = "";
	private password = "";
    private login: string =''; // stocke username et password
    private error: string = ''; // message d'erreur

	private response: string;

	constructor(
		private loginService: LoginService,
		private router: Router) {}

    private loginText(usernameValue: string, passwordValue: string): void {
    	if (usernameValue == '') { // username vide
				this.error = ``;
    		this.error += `Veuillez ne pas laisser vide le username\n`;
    	}
			else if (passwordValue == '') { // password vide
				this.error = ``;
				this.error += `Veuillez ne pas laisser vide le password\n`;
			}
			else {
		this.login += `Added user with username :'${usernameValue}' and password :'${passwordValue}'\n`;
		this.error = ``;
		}
	}

	private authentication(username: string, pw: string): void {
		if (username === ''){
			this.login='Authentication failed.';
		} else {
			this.loginService.authentication(username, pw)
			.then(r => this.response = r).then(r => {
				if (this.response == 'error') {
					this.login = 'Authentication failed.';
				} else {
					this.login = 'Authentication successful.';
					sessionStorage.setItem('id', this.response);
					this.loginService.getSubordinates(this.response)
						.then(subordinates => sessionStorage.setItem('subordinates', JSON.stringify(subordinates)));
					//Mock avec les id des users assignés au Manager
					//sessionStorage.setItem('Users', JSON.stringify([{id: 1}, {id: 2}, {id: 3}]));
					this.router.navigateByUrl(`/overview`);
					//this.router.navigate(['/overview', this.response]);
				}
			}).catch(error => this.login = 'Authentication failed');
		}
	}
}
