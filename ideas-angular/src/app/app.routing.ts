import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/index';
import { OverviewComponent } from './overview/index';

const appRoutes: Routes = [
    { 
		path: 'login', 
		component: LoginComponent 
	},
	{
		path: 'overview',
		component: OverviewComponent
	}
];

export const routing = RouterModule.forRoot(appRoutes);