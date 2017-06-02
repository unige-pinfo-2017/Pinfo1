import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { PaginationModule } from 'ng2-bootstrap/ng2-bootstrap';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';

import { AppComponent }  from './app.component';
import { UserComponent } from './user/user.component';
import { OverviewComponent } from './overview/overview.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import {SidebarComponent} from './overview/sidebar/sidebar.component';
import {TopbarComponent} from './overview/topbar/topbar.component';
import {MiddlescreenComponent} from './overview/middlescreen/middlescreen.component';
import { ChartComponent } from './chart/chart.component';
import {TableComponent } from './table/table.component';
import {EditingMenuComponent} from './table/editing-menu/editing-menu.component';
import {ErrorComponent} from './error.component';

import { AuthGuard } from './guard/auth-guard.service';
import { SubordinateGuard } from './guard/subordinate-guard.service';
import {RoleGuard} from './guard/role-guard.service';

@NgModule({
  imports:      [
	  BrowserModule,
	  AppRoutingModule,
	  HttpModule,
	  FormsModule,
	  ChartsModule,
	  PaginationModule.forRoot(),
	  NgxDatatableModule
  ],
  declarations: [
	  AppComponent,
	  UserComponent,
	  OverviewComponent,
	  LoginComponent,
      SidebarComponent,
      TopbarComponent,
      MiddlescreenComponent,
	  ChartComponent,
	  TableComponent,
      EditingMenuComponent,
      ErrorComponent,

  ],
  bootstrap:    [ AppComponent ],
  providers: [ AuthGuard, SubordinateGuard, RoleGuard ]
})
export class AppModule { }
