import { Component } from '@angular/core';

@Component({
	selector: 'login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css'],

})

export class LoginComponent {
	title = 'I.D.E.A.S';
	private username = "";
	private password = "";
    private login: string =''; // stocke username et password
    private error: string = ''; // message d'erreur

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
}
