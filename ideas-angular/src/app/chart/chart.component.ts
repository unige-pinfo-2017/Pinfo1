import { Component } from '@angular/core';

@Component({
	selector: 'my-chart',
	templateUrl: './chart.component.html',
	styleUrls: ['./chart.component.css'],
})

export class ChartComponent {
	public lineChartData:Array<any> = [
    	{data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A'},
  	];
  	public lineChartLabels:Array<any> = [
		'January', 'February', 'March', 'April', 'May', 'June', 'July'
	];
	public lineChartOptions:any = {
    	responsive: false
  	};
	public lineChartColors:Array<any> = [
	    { // grey
	      backgroundColor: 'rgba(148,159,177,0.2)',
	      borderColor: 'rgba(148,159,177,1)',
	      pointBackgroundColor: 'rgba(148,159,177,1)',
	      pointBorderColor: '#fff',
	      pointHoverBackgroundColor: '#fff',
	      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
	    },
	];
	public lineChartLegend:boolean = true;
  	public lineChartType:string = 'line';
}
