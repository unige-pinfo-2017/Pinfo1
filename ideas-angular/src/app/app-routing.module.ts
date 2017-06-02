import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserComponent }  from './user/user.component';
import { OverviewComponent } from './overview/overview.component';
import { LoginComponent } from './login/login.component';
import { ChartComponent } from './chart/chart.component';
import { TableComponent } from './table/table.component';
import {EditingMenuComponent} from './table/editing-menu/editing-menu.component';
import { AuthGuard } from './guard/auth-guard.service';
import { SubordinateGuard } from './guard/subordinate-guard.service';
import {RoleGuard} from './guard/role-guard.service';
import {ErrorComponent} from './error.component';

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
		path: 'overview/:userId',
		component: OverviewComponent,
		canActivate: [ AuthGuard, SubordinateGuard ]
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
		path: 'editing-menu',
		component: EditingMenuComponent
	},
  {
    path: 'error',
    component: ErrorComponent
  },
  {
	  path: 'table/:type',
	  component: TableComponent,
      canActivate: [AuthGuard, RoleGuard]
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
