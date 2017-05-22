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
					this.setSubordinates(this.response).then( temp =>
						this.setRole(this.response).then( temp =>
							this.router.navigate(['/overview'])
						)
					);
				}
			}).catch(error => this.login = 'Authentication failed');
		}
	}

	private setSubordinates(userId: string): Promise<any> {
		return this.loginService.getSubordinates(userId)
			.then(subordinates => sessionStorage.setItem('subordinates', JSON.stringify(subordinates)));
	}

	private setRole(userId: string): Promise<any> {
		return this.loginService.getRole(this.response)
			.then(role => sessionStorage.setItem('role', role));
	}
}
