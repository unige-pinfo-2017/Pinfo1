import { Component, Output, EventEmitter } from '@angular/core';

@Component({
	selector: 'sidebar',
	templateUrl: './sidebar.component.html',
	styleUrls: ['./sidebar.component.css'],
})

export class SidebarComponent {
	@Output() tableButtonClickEvent = new EventEmitter();

	public callParent() {
		this.tableButtonClickEvent.next();
	}
}
