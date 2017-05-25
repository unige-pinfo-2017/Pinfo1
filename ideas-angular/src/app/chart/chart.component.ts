import { Component, OnInit, ViewChild} from '@angular/core';
import { BaseChartDirective } from 'ng2-charts/ng2-charts';
import { ChartService } from './chart.service';

@Component({
	selector: 'my-chart',
	templateUrl: './chart.component.html',
	styleUrls: ['./chart.component.css'],
	providers: [ ChartService ]
})

export class ChartComponent implements OnInit {
	@ViewChild(BaseChartDirective) chart: BaseChartDirective;


	// Line chart
	public lineChartLegend:boolean = true;
  	public lineChartType:string = 'line';

	public lineChartData:Array<any> = [
    	{data: [ 65, 34, 56 ], label: 'Series A'}
  	];
  	//private lineChartLabels:Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
	private lineChartLabels:Array<any> = ['January', 'February', 'March'];
	private lineChartOptions:any = {
    	responsive: false
  	};
	private lineChartColors:Array<any> = [
	    /*{ // grey
	      backgroundColor: 'rgba(148,159,177,0.2)',
	      borderColor: 'rgba(148,159,177,1)',
	      pointBackgroundColor: 'rgba(148,159,177,1)',
	      pointBorderColor: '#fff',
	      pointHoverBackgroundColor: '#fff',
	      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
	    },
		{ // dark grey
		  backgroundColor: 'rgba(77,83,96,0.2)',
		  borderColor: 'rgba(77,83,96,1)',
		  pointBackgroundColor: 'rgba(77,83,96,1)',
		  pointBorderColor: '#fff',
		  pointHoverBackgroundColor: '#fff',
		  pointHoverBorderColor: 'rgba(77,83,96,1)'
		},
		{ // grey
		  backgroundColor: 'rgba(45,59,67,0.2)',
		  borderColor: 'rgba(45,59,67,1)',
		  pointBackgroundColor: 'rgba(45,59,67,1)',
		  pointBorderColor: '#fff',
		  pointHoverBackgroundColor: '#fff',
		  pointHoverBorderColor: 'rgba(45,59,67,1)'
		}
		*/
	];

	/*
	// Pie chart
	/*public pieChartType:string = 'pie';
	public pieChartLabels:string[] = ['Download Sales', 'In-Store Sales', 'Mail Sales'];
	public pieChartData:number[] = [300, 500, 100];*/

	public constructor (
		private chartService: ChartService
	) {}

	public ngOnInit(): void {
		/*this.getChartDataMock()
			.then(params => {
				console.log(params);
				console.log(params[0]);
				console.log(params[1]);
			})*/
			this.getChartDataMock();
	}

	public getChartDataMock(): void {
		this.chartService.getChartDataMock()
			.then(params => {
				this.lineChartData = this.chartService.formatData(params[0], params[1]);
				this.lineChartLabels = this.chartService.formatLabels(params[2]);
				this.chart.chart.config.data.labels = this.lineChartLabels;
			});
	}

	public switchToBar():void {
		this.lineChartType = this.lineChartType === 'bar' ? 'bar' : 'bar';
	}

	public switchToLine():void {
		this.lineChartType = this.lineChartType === 'line' ? 'line' : 'line';
	}

	public switchToPie():void {
		this.lineChartType = this.lineChartType === 'pie' ? 'pie' : 'pie';
	}

	public switchToRadar():void {
		this.lineChartType = this.lineChartType === 'radar' ? 'radar' : 'radar';
	}

	public switchToPolarArea():void {
		this.lineChartType = this.lineChartType === 'polarArea' ? 'polarArea' : 'polarArea';
	}

	public switchToDoughnut():void {
		this.lineChartType = this.lineChartType === 'doughnut' ? 'doughnut' : 'doughnut';
	}

	public randomize():void {
		let _lineChartData:Array<any> = new Array(this.lineChartData.length);
		for (let i = 0; i < this.lineChartData.length; i++) {
			_lineChartData[i] = {data: new Array(this.lineChartData[i].data.length), label: this.lineChartData[i].label};
		for (let j = 0; j < this.lineChartData[i].data.length; j++) {
			_lineChartData[i].data[j] = Math.floor((Math.random() * 100) + 1);
		}
	}
	this.lineChartData = _lineChartData;
	}
}
