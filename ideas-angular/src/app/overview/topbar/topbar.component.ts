import { Component, Input, Output, EventEmitter } from '@angular/core';


@Component({
	selector: 'topbar',
	templateUrl: './topbar.component.html',
	styleUrls: ['./topbar.component.css'],
})

export class TopbarComponent {
	 @Input() data: any[];
	 @Input() hiddenData: any[];

	 @Output() added: EventEmitter<string> = new EventEmitter();
	 @Output() removed: EventEmitter<string> = new EventEmitter();
	 @Output() changed: EventEmitter<string> = new EventEmitter();

	 public addLiveData(name: string): void {
		 this.added.emit(name);
	 }

	 public changeLiveData(name: string): void {
		 this.changed.emit(name);
	 }

	 public removeLiveData(name: string): void {
		 //console.log("remove called");
		 this.removed.emit(name);
	 }
}
