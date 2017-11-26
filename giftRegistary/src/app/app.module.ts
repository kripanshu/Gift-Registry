import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { ForgetPassowrdComponent } from './components/forget-passowrd/forget-passowrd.component';
import {DataService} from './services/data.service';
import {LoginService} from './services/login.service';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FlashMessagesModule } from 'angular2-flash-messages';
import {SharedService} from './services/shared.service';
import {RegisterService} from './services/register.service';
import {RegisteryService} from './services/registery.service';
import { AboutUsComponent } from './components/about-us/about-us.component';
// create routers
const appRoutes : Routes=[
  {path:'',component : WelcomeComponent},
  {path:'register',component : RegisterComponent},
  {path:'login',component : LoginComponent},
  {path:'forgetpasswrd',component : ForgetPassowrdComponent},
  {path:'dashboard',component : DashboardComponent},
  {path:'aboutus', component: AboutUsComponent }

];
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    WelcomeComponent,
    ForgetPassowrdComponent,
    DashboardComponent,
    AboutUsComponent
    
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    FlashMessagesModule.forRoot(),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [DataService, LoginService, SharedService, RegisterService, RegisteryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
