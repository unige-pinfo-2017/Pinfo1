import { Component, Input } from '@angular/core';

@Component({
	selector: 'topbar',
	templateUrl: './topbar.component.html',
	styleUrls: ['./topbar.component.css'],
})

export class TopbarComponent {
	 @Input() data: any[];
	 @Input() hiddenData: any[];

	 public deleteLiveData(): void {
		 // TO DO: effacer element de la liste pour ne plus l'afficher dans topbar 
	 }
}
