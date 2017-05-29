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
  	//private lineChartLabels:Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
	private lineChartLabels:Array<any> = [];
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
	    }
		*/
		{ // dark grey
		  backgroundColor: 'rgba(77,83,96,0.2)',
		  borderColor: 'rgba(77,83,96,1)',
		  pointBackgroundColor: 'rgba(77,83,96,1)',
		  pointBorderColor: '#fff',
		  pointHoverBackgroundColor: '#fff',
		  pointHoverBorderColor: 'rgba(77,83,96,1)'
		},
		/*
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

	public shortChartType: string;
	public longChartType: string;
	public resource: string;
	private years: number[] =[];

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
			this.shortChartType = "day";
			this.resource = "temperature";
			this.getChartData(this.resource, this.shortChartType);
			this.years = this.getYears();
	}

	public getChartData(resource: string, type: string): void {
		this.chartService.getChartShort(resource, type)
			.then(params => {
				this.lineChartData = this.chartService.formatData(params[0], params[1]);
				this.lineChartLabels = this.chartService.formatLabels(params[2]);
				this.chart.chart.config.data.labels = this.lineChartLabels; // Workaround
			});
	}

	public onChange(newTime: string):void {
		this.shortChartType = newTime;
		this.getChartData(this.resource, this.shortChartType);
	}

	public switchToBar():void {
		this.lineChartType = this.lineChartType === 'bar' ? 'bar' : 'bar';
	}

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
	/*public switchToPie():void {
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
	}*/

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

	public chartClicked(e:any):void {
		console.log(e);
	}

	public chartHovered(e:any):void {
		console.log(e);
	}
}
