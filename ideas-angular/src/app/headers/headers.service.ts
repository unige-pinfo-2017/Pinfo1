import { Injectable } from '@angular/core';
import { Headers } from '@angular/http';

@Injectable()
export class HeadersService {

	public getHeadersJson(): Headers {
		let headers = new Headers();
		headers.append('Accept', 'application/json');
		headers.append('Content-type', 'application/json');
		return headers;
	}

	public getHeadersJsonCORS(): Headers {
		let headers = new Headers();
		headers.append('Accept', 'application/json');
		headers.append('Content-type', 'application/json');
		headers.append('Access-Control-Allow-Origin','*')
		return headers;
	}

	public getHeadersPlainText(): Headers {
		let headers = new Headers();
		headers.append('Accept', 'text/plain');	// Spécifie le type de données accepté
		headers.append('Content-Type', 'text/plain');
		return headers;
	}
}
