import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
	private baseUrl: string = 'http://localhost:8080/IDEAS';

	constructor(private http: Http) {}

	getReadings(): Observable<Number[]> {
		let readings = this.http
		.get(`${this.baseUrl}/overview/live`, {headers: this.getHeaders()})
		.map(res => <Number[]>res.json());

		return readings;
	}

	private getHeaders() {
		// Permet de récupérer les headers
		let headers = new Headers();
		headers.append('Accept', 'application/json');	// Spécifie le type de données accepté
		headers.append('Content-Type', 'application/json');
		return headers;
	}
}
