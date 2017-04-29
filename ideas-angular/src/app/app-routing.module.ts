import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserComponent }  from './user/user.component';
import { OverviewComponent } from './overview/overview.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  	/*{
		path: '', redirectTo: '/overview',
		pathMatch: 'full'
	},*/
  	{
		path: 'user',
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
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
