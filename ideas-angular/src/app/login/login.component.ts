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
    private login: string ='';
    private error: string = '';

    private loginText(usernameValue: string, passwordValue: string): void {
    	if (usernameValue == '') {
				this.error = ``;
    		this.error += `Veuillez ne pas laisser vide le username\n`;
    	}
			else if (passwordValue == '') {
				this.error = ``;
				this.error += `Veuillez ne pas laisser vide le password\n`;
			}
			else {
		this.login += `Added user with username :'${usernameValue}' and password :'${passwordValue}'\n`;
		this.error = ``;
		}
	}
}
