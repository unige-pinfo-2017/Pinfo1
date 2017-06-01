import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { HeadersService } from '../headers/headers.service';

import 'rxjs/add/operator/map';

@Injectable()
export class OverviewService {
	private baseUrl: string = 'http://localhost:8080/IDEAS/rest';

	constructor(private http: Http,
				private headersService: HeadersService
	) {}

	public getLiveData(userId: number): Promise<Array<any>> {
		return this.http
		.get(`${this.baseUrl}/overview/live-data/${userId}`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}

	public getHiddenData(userId: number): Promise<Array<any>> {
		return this.http
		.get(`${this.baseUrl}/overview/hidden-data/${userId}`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}

	public addLiveData(userId: number, name: string): Promise<Response> {
		return this.http.post(`${this.baseUrl}/overview/preferences/${userId}/add`, name, {headers: this.headersService.getHeadersPlainText()})
			.toPromise();
	}

	public removeLiveData(userId: number, name: string): Promise<Response> {
		return this.http.post(`${this.baseUrl}/overview/preferences/${userId}/remove`, name, {headers: this.headersService.getHeadersPlainText()})
			.toPromise();
	}
}
