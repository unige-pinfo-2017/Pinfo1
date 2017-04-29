import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from './user';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {
	private baseUrl: string = 'http://localhost:8080/IDEAS';
	private testUrl: string = 'http://localhost:3000';

	constructor(private http: Http) {}

	getUser(id: number): Observable<User> {
		let user$ = this.http // var$ suffix -> var est un Observable
	      .get(`${this.testUrl}/user/${id}`, {headers: this.getHeaders()})
	      .map(this.mapUser);

		  return user$;
	}

	private getHeaders() {
		let headers = new Headers();
		headers.append('Accept', 'application/json');
		headers.append('Content-Type', 'application/json');
		return headers;
	}

	private toUser(r: any): User {
		return r;
	}

	private mapUser(response: Response): User {
		return this.toUser(response.json());
	}
}
