import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserComponent }  from './user/user.component';
import { OverviewComponent } from './overview/overview.component';
import { LoginComponent } from './login/login.component';
import { ChartComponent } from './chart/chart.component';
import { TableComponent } from './table/table.component';

const routes: Routes = [
  	{
		path: '', redirectTo: '/login',
		pathMatch: 'full'
	},
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
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
