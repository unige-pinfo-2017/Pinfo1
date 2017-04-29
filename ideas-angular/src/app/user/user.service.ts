import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from './user';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
	private baseUrl: string = 'http://localhost:8080/IDEAS';
	//private testUrl: string = 'http://localhost:3000';

	constructor(private http: Http) {}

	getUser(id: number): Observable<User> {
		let user$ = this.http
			 .get(`${this.baseUrl}/user/${id}`, {headers: this.getHeaders()}) // Récupère une donnée à l'url du serveur
			 .map(res => <User>res.json()); // Conversion des données formatées en JSON vers la classe User
		return user$;
	}

	private getHeaders() {
		// Permet de récupérer les headers
		let headers = new Headers();
		headers.append('Accept', 'application/json');	// Spécifie le type de données accepté
		headers.append('Content-Type', 'application/json');
		return headers;
	}

	/*getUserTest(id: number): Observable<User> {
		let user$ = this.http // var$ suffix -> var est un Observable
	      .get(`${this.testUrl}/user/${id}`, {headers: this.getHeaders()})
	      .map(this.mapUser);
		  return user$;
	}*/

	/*getUser2(id: number): Observable<User> {
		let user$ = this.http // var$ suffix -> var est un Observable
	      .get(`${this.baseUrl}/user/${id}`, {headers: this.getHeaders()})
	      .map(this.mapUser);

		  return user$;
	}*/

	/*private toUser(r: any): User {
		return r;
	}*/

	/*private mapUser(response: Response): User {
		return this.toUser(response.json());
	}*/


}
