import { Component, Input } from '@angular/core';

@Component({
	selector: 'topbar',
	templateUrl: './topbar.component.html',
	styleUrls: ['./topbar.component.css'],
})

export class TopbarComponent {
	 @Input() data: any[];
	 @Input() hiddenData: any[];

	 public deleteLiveData(name: string): void {
		 // TO DO: effacer element de la liste pour ne plus l'afficher dans topbar
		 console.log(name);
	 }

	 public addLiveData(name: string): void {
		 console.log(name);
	 }

	 public changeLiveData(name: string): void {
		 console.log(name);
	 }
}
