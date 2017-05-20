import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { HeadersService } from '../headers/headers.service';
import { User } from './user';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
	private baseUrl: string = 'http://localhost:8080/IDEAS';
	//private testUrl: string =	 'http://localhost:3000';

	constructor(private http: Http,
				private headersService: HeadersService,
				) {}

	getUser(id: number): Observable<User> {
		let user$ = this.http
			 .get(`${this.baseUrl}/user/byId/${id}`, {headers: this.headersService.getHeadersJson()}) // Récupère une donnée à l'url du serveur
			 .map(res => <User>res.json()); // Conversion des données formatées en JSON vers la classe User
		return user$;
	}

	getUserByUsername(username: string): Observable<User>{
		let user$ = this.http
		.get(`${this.baseUrl}/user/byUsername/${username}`, {headers: this.headersService.getHeadersJson()}) // Récupère une donnée à l'url du serveur
		.map(res => <User>res.json()); // Conversion des données formatées en JSON vers la classe User
   		return user$;
	}
}
