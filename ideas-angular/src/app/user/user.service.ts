import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from './user';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
	private baseUrl: string = 'http://localhost:8080/IDEAS';
	//private testUrl: string =	 'http://localhost:3000';

	constructor(private http: Http) {}

	getUser(id: number): Observable<User> {
		let user$ = this.http
			 .get(`${this.baseUrl}/user/byId/${id}`, {headers: this.getHeaders()}) // Récupère une donnée à l'url du serveur
			 .map(res => <User>res.json()); // Conversion des données formatées en JSON vers la classe User
		return user$;
	}

	getUserByUsername(username: string): Observable<User>{
		let user$ = this.http
		.get(`${this.baseUrl}/user/byUsername/${username}`, {headers: this.getHeaders()}) // Récupère une donnée à l'url du serveur
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
}
