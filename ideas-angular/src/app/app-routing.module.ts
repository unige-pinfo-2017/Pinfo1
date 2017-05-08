import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserComponent }  from './user/user.component';
import { OverviewComponent } from './overview/overview.component';
import { LoginComponent } from './login/login.component';
import { ChartComponent } from './chart/chart.component';
import { TableComponent } from './table/table.component';
import { TableAltComponent } from './table/table-alt.component';

const routes: Routes = [
  	/*{
		path: '', redirectTo: '/overview',
		pathMatch: 'full'
	},*/
  	{
		path: 'user/:username',
		component: UserComponent
	},
	{
		path: 'overview',
		component: OverviewComponent
	},
	{
		path: 'login',
		component: LoginComponent
	},
	{
		path: 'chart',
		component: ChartComponent
	},
	{
		path: 'table',
		component: TableComponent
	},
	{
		path: 'table-alt',
		component: TableAltComponent
	}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
