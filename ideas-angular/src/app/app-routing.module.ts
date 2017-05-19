import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserComponent }  from './user/user.component';
import { OverviewComponent } from './overview/overview.component';
import { LoginComponent } from './login/login.component';
import { ChartComponent } from './chart/chart.component';
import { TableComponent } from './table/table.component';
import {EditingMenuComponent} from './table/editing-menu/editing-menu.component';
import { AuthGuard } from './guard/auth-guard.service';

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
		component: OverviewComponent,
		canActivate: [ AuthGuard ]
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
		component: TableComponent,
		canActivate: [ AuthGuard ]
	},
	{
		path: 'editing-menu',
		component: EditingMenuComponent
	},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
