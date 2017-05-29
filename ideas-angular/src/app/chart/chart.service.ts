import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { HeadersService } from '../headers/headers.service';

@Injectable()
export class ChartService {
	private baseUrl: string = 'http://localhost:8080/IDEAS';

	constructor (
		private http: Http,
		private headersService: HeadersService
	) {}

	public getChartDataMock(): Promise<any[]> {
		return this.http.get(`${this.baseUrl}/chart/temperature/last-day`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}

	public getChartShort(resource: string, time: string): Promise<any[]> {
		return this.http.get(`${this.baseUrl}/chart/${resource}/mock-last-${time}`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}

	public getChartYear(resource: string, yearNum:number): Promise<any[]> {
		return this.http.get(`${this.baseUrl}/chart/${resource}/year/${yearNum}`, {headers: this.headersService.getHeadersJson()})
		.toPromise()
		.then(res => res.json() as Array<any>)
	}

	public getChartLong(resource: string, from: string, to: string) {

	}

	public formatData(data: any[], label: any): any[] {
		//let values = [{value: 2}, {value: 4}, {value: 3}]
		//let label = {label: "bob"};
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
		//let l = [{label: "Monday"}, {label:"Tuesday"}, {label:"Wednesday"}];
		let formattedLabels:any[] = [];
		for (let i=0; i<labels.length; i++) {
			formattedLabels.push(labels[i].label);
		}
		return formattedLabels;
	}
}
