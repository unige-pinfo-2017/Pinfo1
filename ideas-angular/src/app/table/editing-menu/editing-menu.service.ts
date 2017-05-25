import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';

import { HeadersService } from '../../headers/headers.service';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class EditingMenuService {
	private baseUrl: string = "http://localhost:8080/IDEAS";

	constructor(private http: Http,
				private headersService: HeadersService
				) {}

	public getEditingMenu(deviceId: any): Promise<any[]> {
		return this.http
		.get(`${this.baseUrl}/edit/${deviceId}/menu`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>);
	}
}
