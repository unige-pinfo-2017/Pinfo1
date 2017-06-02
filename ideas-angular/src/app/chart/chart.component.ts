import { Component, OnInit, ViewChild} from '@angular/core';
import { BaseChartDirective } from 'ng2-charts/ng2-charts'; // Workaround
import { ChartService } from './chart.service';

@Component({
	selector: 'my-chart',
	templateUrl: './chart.component.html',
	styleUrls: ['./chart.component.css'],
	providers: [ ChartService ]
})

export class ChartComponent implements OnInit {
	@ViewChild(BaseChartDirective) chart: BaseChartDirective; // Workaround of can't update labels

	// Line chart
	public lineChartLegend:boolean = true;
  	public lineChartType:string = 'line';

	public lineChartData:Array<any> = [
    	{data: [], label: 'Default'}
  	];

	private lineChartLabels:Array<any> = [];
	private lineChartOptions:any = {
    	responsive: false
  	};
	private lineChartColors:Array<any> = [
	    { // blue
	      backgroundColor: 'rgba(0,98,140,0.5)',
	      borderColor: 'rgba(148,159,177,1)',
	      pointBackgroundColor: 'rgba(148,159,177,1)',
	      pointBorderColor: '#fff',
	      pointHoverBackgroundColor: '#fff',
	      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
	    }
	];

	public shortChartType: string;
	public longChartType: string;
	public resource: string;
	private years: number[] =[];
	private selectedYear: number;

	public constructor (
		private chartService: ChartService
	) {}

	public ngOnInit(): void {
			this.shortChartType = "day";
			this.resource = "temperature";
			this.getChartDataShort(this.resource, this.shortChartType);
			this.years = this.getYears();
	}

	public getChartDataShort(resource: string, type: string): void {
		this.chartService.getChartShort(resource, type)
			.then(params => {
				this.resource = resource;
				this.shortChartType = type;

				// params[0] is the data (y-axis), params[1] is the name of the curve, params[2] is the labels (x-axis)
				this.lineChartData = this.chartService.formatData(params[0], params[1]);
				this.lineChartLabels = this.chartService.formatLabels(params[2]);
				this.chart.chart.config.data.labels = this.lineChartLabels; // Workaround
			});
	}

	public getChartDataYear(resource: string, yearNum: number): void {
		this.chartService.getChartYear(resource, yearNum)
			.then(params => {
				this.resource = resource;

				// params[0] is the data (y-axis), params[1] is the name of the curve, params[2] is the labels (x-axis)
				this.lineChartData = this.chartService.formatData(params[0], params[1]);
				this.lineChartLabels = this.chartService.formatLabels(params[2]);
				this.chart.chart.config.data.labels = this.lineChartLabels; // Workaround
			});
	}

	public switchToBar():void {
		this.lineChartType = this.lineChartType === 'bar' ? 'bar' : 'bar';
	}*/

	public switchToLine():void {
		this.lineChartType = this.lineChartType === 'line' ? 'line' : 'line';
	}

	private getYears(): number[] {
		let years: number[] = [];
		let today = new Date();
		let yy = today.getFullYear();
		for(var i = (yy-100); i <= yy; i++){
		   years.push(i);
		}
		return years;
   }

	/*public randomize():void {
		let _lineChartData:Array<any> = new Array(this.lineChartData.length);
		for (let i = 0; i < this.lineChartData.length; i++) {
			_lineChartData[i] = {data: new Array(this.lineChartData[i].data.length), label: this.lineChartData[i].label};
		for (let j = 0; j < this.lineChartData[i].data.length; j++) {
			_lineChartData[i].data[j] = Math.floor((Math.random() * 100) + 1);
		}
	}
		this.lineChartData = _lineChartData;
	}*/

	public chartClicked(e:any):void {
		//console.log(e);
	}

	public chartHovered(e:any):void {
		//console.log(e);
	}
}
