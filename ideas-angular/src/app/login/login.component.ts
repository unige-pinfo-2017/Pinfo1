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

    private loginText(usernameValue: string, passwordValue: string): void {
		this.login += `Added user with username :'${usernameValue}' and password :'${passwordValue}'\n`; 
	}
}
