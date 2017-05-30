import { Component, ViewChild } from '@angular/core';
import { ChartComponent } from '../../chart/chart.component';

@Component({
	selector: 'middlescreen',
	templateUrl: './middlescreen.component.html',
	styleUrls: ['./middlescreen.component.css'],
})

export class MiddlescreenComponent {
	@ViewChild(ChartComponent) child: ChartComponent;

	public getChartDataShort(resource: string, type: string): void {
		this.child.getChartDataShort(resource, type);
	}
}
