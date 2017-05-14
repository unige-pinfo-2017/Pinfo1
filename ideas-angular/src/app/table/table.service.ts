import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';

import { HeadersService } from '../headers/headers.service';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class TableService {
	private baseUrl: string = "http://localhost:8080/IDEAS";

	constructor(private http: Http,
				private HeadersService: HeadersService
				) {}

	public getTable(userId: number, deviceType: string): Promise<any[]> {
		return this.http
		.get(`${this.baseUrl}/table/${userId}/${deviceType}`, {headers: this.HeadersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
		.catch(this.handleError);
	}

	/*public getDeviceColumns(deviceType: string): Promise<Array<any>> {
		// Récupère un Observable contenant les colonnes d'un type de Device
		return this.http
		.get(`${this.baseUrl}/device/columns?type=${deviceType}`, {headers: this.getHeaders()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}

	public getDeviceData(deviceType: string): Promise<Array<any>> {
		// Récupère un Promise des données d'une liste d'un certain type de device
		return this.http
		.get(`${this.baseUrl}/device/data?type=${deviceType}`, {headers: this.getHeaders()})
		.toPromise()
		.then(res => res.json() as Array<any>)
		.catch(this.handleError);
	}*/



	// http://localhost:8080/IDEAS/table/columns?type=PowerSocket&userid=1

	/*public getColumnsDemo(): Promise<Array<any>> {
		// Récupère un Promise des colonnes d'une liste d'un certain type de device
		return this.http
		.get(`${this.baseUrl}/demo/columns`, {headers: this.getHeaders()})
		.toPromise()
		.then(res => res.json() as Array<any>);
	}

	public getDataDemo(): Promise<Array<any>> {
		return this.http
		.get(`${this.baseUrl}/demo/data`, {headers: this.getHeaders()})
		.toPromise()
		.then(res => res.json() as Array<any>)
		.catch(this.handleError);
	}*/

	/*private getHeaders() {
		let headers = new Headers();
		headers.append('Accept', 'application/json');	// Spécifie le type de données accepté
		headers.append('Content-Type', 'application/json');
		return headers;
	}*/

	private handleError(error: any): Promise<any> {
		console.error('An error has occured', error);
		return Promise.reject(error.message || error);
	}
}
