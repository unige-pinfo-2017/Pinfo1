import { Component, ViewEncapsulation, ViewChild, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Subscription } from "rxjs";
import { TimerObservable } from "rxjs/observable/TimerObservable";

import { TableService } from './table.service';

//@import '/node_modules/@swimlane/ngx-datatable/release/datatable.css';
//@import '/node_modules/@swimlane/ngx-datatable/release/themes/material.css';
//@import '/node_modules/@swimlane/ngx-datatable/release/assets/icons.css';

@Component({
  	selector: 'my-table',
	templateUrl: './table.component.html',
	styleUrls: [
	'./material.scss',
	'/node_modules/@swimlane/ngx-datatable/release/themes/material.css',
	//'./material.css'
	'./datatable.css',
	//'./style.css'
	'./table.component.css',
	],
	providers: [
		TableService,
	],
	encapsulation: ViewEncapsulation.None
})


export class TableComponent implements OnInit, OnDestroy {

	@ViewChild('myTable') table: any;

	private refreshRate: number = 60*1000; // Fréquence de rafraichissement en milliseconde
	private timerSubscription: Subscription;

	private routeSubscription: Subscription;
    public show: boolean = false;
	private currentType: string;
	private currentSubtype: string;
	private rows:any[] = [];
	private filteredRows: any[] = [];
	private temp: any[] = [];
	private columns:any[] = [];
	private filterString: string = "";
    public showDevice: boolean = false;
    public showSensor: boolean = false;

	expanded: any = {};
	selected: any[] = [];
	timeout: any;

	private columnsExample:any[] = [
	    { "prop": "deviceId" },
	    { "prop": "power" },
	    { "prop": "status" },
		{ "prop": "current" },
		{ "prop": "state"}
	];

	private rowsExample:any[] = [

		{
			"deviceId":	"1",
			"power":	"12.0",
			"status":	"1.0",
			"current": 	"4.0",
			"state": 	"<b><font color='green'>ON</font></b>"
		},
		{
			"deviceId":	"2",
			"power":	"9.0",
			"status":	"2.0",
			"current": 	"9.0",
			"state": 	"<b><font color='red'>OFF</font></b>"
		},
	];

	public constructor(
		private tableService: TableService,
		private router: Router,
        private route: ActivatedRoute,
	) {}

	public ngOnInit(): void {
		this.route.params.subscribe(params => {
			console.log(params);
			let flag:boolean = this.checkType(params['type']);
			if (flag) {
				this.getTableFromStorage(this.currentType, this.currentSubtype);
				this.startTimer(this.refreshRate);
			}
		});
		this.currentType="device";
		this.currentSubtype="PowerSocket";

		this.getTableFromStorage(this.currentType, this.currentSubtype);
		this.startTimer(this.refreshRate);


        //this.showDevice = !this.showDevice;

        //this.showSensor = !this.showSensor;
	}

	public ngOnDestroy(): void {
		if (this.timerSubscription != null) this.timerSubscription.unsubscribe();
		if (this.routeSubscription != null) this.routeSubscription.unsubscribe();
	}

	public getDemo(){
		this.columns = this.columnsExample;
		this.rows = this.rowsExample;
	}

	public getTableFromStorage(type: string, subtype: string):  void {
		this.getTable(Number(sessionStorage.getItem('id')), type, subtype);
	}

	public getTable(userId: number, type: string, subtype: string): void {
		this.tableService.getTable(userId, type, subtype).then(arr => {
				this.columns = arr[0];
				this.rows = arr[1];
				this.currentType = type;
				this.currentSubtype = subtype;

				this.filteredRows = this.rows;
			});
	}

	public startTimer(refreshRate: number): void {
		// Rafraichit la liste des données toutes les N millisecondes
		let timer = TimerObservable.create(0, refreshRate);
		this.timerSubscription = timer.subscribe(t => {
			this.getTableFromStorage(this.currentType, this.currentSubtype);
			console.log("table refreshing");
		})
	}

	public filtering(val:any): any[] {
		let filtered: any[] = [];
		for(let i=0; i<(this.rows.length); i++){
			let r = this.rows[i];
			if (r[Object.keys(r)[1]].toLowerCase().indexOf(val) !== -1) {
				filtered.push(this.rows[i]);
			}
		}
		if (filtered.length > 0) {
			return filtered;
		}
		return this.rows;
	}

	public updateFilter(event: any) {
		let val = event.target.value.toLowerCase();
		console.log(val);
		//console.log(val);
		this.filteredRows = this.filtering(val);
	}

	onSelect(event: any) {
	  //console.log(this.currentType);
		// console.log('Event: select', event, this.selected);
		console.log(this.currentType);
		if((this.currentType === "device") || (this.currentType === "sensor")) {
				this.show = !this.show;
			} else if (this.currentType === "user") {
				let selectedUserId = this.selected[0]['UserId'];
				this.router.navigate(['/overview', selectedUserId]);
		}
	}

    onActivate(event: any) {}

	public checkType(type: string): boolean {
		if (type == "device") {
			this.currentType = "device";
			this.currentSubtype = "PowerSocket";
		} else if (type == "sensor") {
			this.currentType = "sensor";
			this.currentSubtype = "powerSensor";
		} else if (type == "user") {
			this.currentType = "user";
			this.currentSubtype = "all";
		} else {
			this.router.navigate(['/overview']);
			return false;
		}
		return true;
	}

	public refresh() {
	this.getTable(Number(sessionStorage.getItem('id')), this.currentType, this.currentSubtype);
	}

    public displaySubType(): void {
        if (this.currentType === "device") {
            this.showDevice = !this.showDevice;
        }
        else if (this.currentType === "sensor") {
            this.showSensor = !this.showSensor;
        }
    }
}
