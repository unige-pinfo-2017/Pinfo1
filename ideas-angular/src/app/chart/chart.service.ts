import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { HeadersService } from '../headers/headers.service';

@Injectable()
export class ChartService {
	private baseUrl: string = 'http://localhost:8080/IDEAS/rest';

	constructor (
		private http: Http,
		private headersService: HeadersService
	) {}

	/*public getChartDataMock(): Promise<any[]> {
		return this.http.get(`${this.baseUrl}/chart/temperature/last-day`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}*/

	public getChartShort(resource: string, time: string): Promise<any[]> {
		// Received JSON format:  [{value: x}, {value: y}, {value: z}]
		return this.http.get(`${this.baseUrl}/chart/${resource}/last-${time}`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}

	public getChartYear(resource: string, yearNum:number): Promise<any[]> {
		// Received JSON format: [{label: a}, {label: b}, {label: c}]
		return this.http.get(`${this.baseUrl}/chart/${resource}/year/${yearNum}`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}

	public getChartLong(resource: string, from: string, to: string) {

	}

	public formatData(data: any[], label: any): any[] {
		// Convert data JSON received from servers to readable fields for the ng2-charts framework
		// from: [{value: x}, {value: y}, {value: z}] and {label: A}
		// to: {data: [x, y, z], label: 'A'}
		let chartData: Object =  {};
		let formattedData: any[] = [];

		for (let i=0; i<data.length; i++) {
			formattedData.push(data[i].value);
		}

		chartData['data'] = formattedData;
		chartData['label'] = label.label;
		return [chartData];
	}

	public formatLabels(labels: any[]): any[] {
		// Covert labels JSON received from servers to readble fields for the ng2-charts framework
		// from: [{label: a}, {label: b}, {label: c}]
		// to: [a, b, c]
		let formattedLabels:any[] = [];
		for (let i=0; i<labels.length; i++) {
			formattedLabels.push(labels[i].label);
		}
		return formattedLabels;
	}
}
