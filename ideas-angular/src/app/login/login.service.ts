import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class LoginService {
	private baseUrl: string = "http://localhost:8080/IDEAS";

	constructor(private http: Http) {}

	authentication(username: string, pw: string): Promise<string> {
		return this.http
			.get(`${this.baseUrl}/login?username=${username}&pw=${pw}`, {headers: this.getHeaders()})
			.toPromise()
			.then(res => res.text() as string)
	}

	/*
	getHeroes(): Promise<Hero[]> {
		return this.http.get(this.heroesUrl)
			  .toPromise()
			.then(response => response.json().data as Hero[])
			.catch(this.handleError);
	}
	*/

	private getHeaders() {
		let headers = new Headers();
		headers.append('Accept', 'text/plain');	// Spécifie le type de données accepté
		headers.append('Content-Type', 'text/plain');
		return headers;
	}
}
