import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { Ng2TableModule } from 'ng2-table/ng2-table';
import { ChartsModule } from 'ng2-charts/ng2-charts';
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

import { AuthGuard } from './guard/auth-guard.service';

@NgModule({
  imports:      [
	  BrowserModule,
	  AppRoutingModule,
	  HttpModule,
	  FormsModule,
	  Ng2TableModule,
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
  ],
  bootstrap:    [ AppComponent ],
  providers: [ AuthGuard ]
})
export class AppModule { }
