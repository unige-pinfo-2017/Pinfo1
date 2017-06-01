import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';

import { HeadersService } from '../headers/headers.service';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class TableService {
	private baseUrl: string = "http://localhost:8080/IDEAS/rest";

	constructor(private http: Http,
				private headersService: HeadersService
				) {}

	public getTable(userId: number, type: string, subtype: string): Promise<any[]> {
		// type = device ou sensor
		// subtype = PowerSocket, Light, etc pour device
		// et powerSensor, lightSensor, etc pour sensor
		return this.http
		.get(`${this.baseUrl}/table/${userId}/${type}/${subtype}`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
		.catch(this.handleError);
	}

	private handleError(error: any): Promise<any> {
		console.error('An error has occured', error);
		return Promise.reject(error.message || error);
	}
}
