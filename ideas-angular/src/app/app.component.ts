import { Component } from '@angular/core';

import { HeadersService } from './headers/headers.service';


@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.css' ],
  providers: [ HeadersService],
})
export class AppComponent  {
	name = 'Angular';
}
