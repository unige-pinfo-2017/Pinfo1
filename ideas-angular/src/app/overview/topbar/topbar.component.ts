import { Component, Input } from '@angular/core';

@Component({
	selector: 'topbar',
	templateUrl: './topbar.component.html',
	styleUrls: ['./topbar.component.css'],
})

export class TopbarComponent {
	 @Input() data: any[];
	 @Input() hiddenData: any[];
}
