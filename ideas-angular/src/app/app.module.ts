import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';


import { AppComponent }  from './app.component';
import { UserComponent } from './user/user.component';
import { OverviewComponent } from './overview/overview.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  imports:      [
	  BrowserModule,
	  AppRoutingModule,
	  HttpModule,
	  FormsModule
  ],
  declarations: [
	  AppComponent,
	  UserComponent,
	  OverviewComponent,
	  LoginComponent
  ],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
