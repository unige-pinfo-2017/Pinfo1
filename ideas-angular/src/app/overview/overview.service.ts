import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class OverviewService {
	private baseUrl: string = 'http://localhost:8080/IDEAS/overview';

	constructor(private http: Http) {}

	public getLiveData(userId: number): Promise<Array<any>> {
		return this.http
		.get(`${this.baseUrl}/live?userid=${userId}`, {headers: this.getHeaders()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}

	private getHeaders() {
		// Permet de récupérer les headers
		let headers = new Headers();
		headers.append('Accept', 'application/json');	// Spécifie le type de données accepté
		headers.append('Content-Type', 'application/json');
		return headers;
	}
}
