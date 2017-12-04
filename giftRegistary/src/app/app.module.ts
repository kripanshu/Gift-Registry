import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
//import { NotfoundComponent } from './notfound/notfound.component';
import { SocialLoginModule, AuthServiceConfig } from "angular4-social-login";
import { GoogleLoginProvider, FacebookLoginProvider } from "angular4-social-login";

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
import {InventoryService} from './services/inventory.service';
import {SharedregistryService} from './services/sharedregistry.service'
import { AboutUsComponent } from './components/about-us/about-us.component';
import { CreateRegistryComponent } from './components/create-registry/create-registry.component';
import {NamesharedService} from './services/nameshared.service';
import { UserRegistryComponent } from './components/user-registry/user-registry.component';
import { UserInventoryComponent } from './components/user-inventory/user-inventory.component';
import { AdminInventoryComponent } from './components/admin-inventory/admin-inventory.component';
import { SharedregistryComponent } from './components/sharedregistry/sharedregistry.component';
import { ErrorpageComponent } from './components/errorpage/errorpage.component'
// create routers
const appRoutes : Routes=[
  {path:'',component : WelcomeComponent},
  {path:'register',component : RegisterComponent},
  {path:'login',component : LoginComponent},
  {path:'forgetpasswrd',component : ForgetPassowrdComponent},
  {path:'dashboard',component : DashboardComponent},
  {path:'aboutus', component: AboutUsComponent },
  {path:'create-registry', component: CreateRegistryComponent},
  {path: 'user-registry/:regurl', component:UserRegistryComponent},
  {path: 'user-inventory',component:UserInventoryComponent},
  {path: 'admin-inventory',component:AdminInventoryComponent},
  {path: 'shared-registry/:regurl', component:SharedregistryComponent},
  {path:' ** ',component:ErrorpageComponent}

];

let config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider("40798009125-k4bk3cide7ejf0kbcgej0r4hnrelrsad.apps.googleusercontent.com")
  },
  {
    id: FacebookLoginProvider.PROVIDER_ID,
    provider: new FacebookLoginProvider("Facebook-App-Id")
  }
]);
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    WelcomeComponent,
    ForgetPassowrdComponent,
    DashboardComponent,
    AboutUsComponent,
    CreateRegistryComponent,
    UserRegistryComponent,
    UserInventoryComponent,
    AdminInventoryComponent,
    SharedregistryComponent,
    ErrorpageComponent
    
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    FlashMessagesModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    Ng2SmartTableModule,
    NgbModule.forRoot(),
    SocialLoginModule.initialize(config)
  ],
  providers: [DataService, LoginService, SharedService, RegisterService, RegisteryService, NamesharedService, InventoryService,SharedregistryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
