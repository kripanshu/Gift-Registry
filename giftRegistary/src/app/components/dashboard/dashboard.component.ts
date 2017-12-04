import { Component, OnInit } from '@angular/core';
import {SharedService} from '../../services/shared.service';
import {Credentials} from '../../models/Credentials';
import {RegisterService} from '../../services/register.service';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
import { log } from 'util';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
firstName:string;
lastName:string;
email:string;
password:string;
street:string;
state:string;
zipcode:string;
country:string;
securityQuestion:string;
securityAnswer:string;
dashboard_data:Credentials ={
  
    firstName: this.firstName,
    lastName: this.lastName,
    email: this.email,
    securityQuestion:this.securityQuestion,
    securityAnswer:this.securityAnswer,
    password:this.password,
    street:this.street,
    state:this.state,
    zipcode:this.zipcode,
    country:this.country
  
  
  
  };


  constructor(private sharedService: SharedService,
  private registerService:RegisterService,
  private router: Router,
  private flashMessagesService: FlashMessagesService
  ) { }
  loggedInUserEmail:string;
  ngOnInit() {
    this.sharedService.message.subscribe(message => {
      this.loggedInUserEmail = message;

      if(this.loggedInUserEmail=='')
      {
        this.router.navigate(['login']);
  
      }

    });
    this.getUserData(this.loggedInUserEmail);
  }
getUserData(email:string)
{

this.registerService.getUserProfileData(email).subscribe( res =>{
  
  console.log("Get user profile ",res.json().firstName);
  this.dashboard_data=res.json();
  // {

  //   firstName: res.json().firstName,
  //   lastName: res.json().lastName,
  //   email: res.json().email,
  //   securityQuestion:res.json().securityQuestion,
  //   securityAnswer:res.json().securityAnswer,
  //   password:res.json().password,
  //   street:res.json().street,
  //   state:res.json().state,
  //   zipcode:res.json().zipcode,
  //   country:res.json().country
  
  // };
  //console.log(this.dashboard_data);
},
error => {
  console.log(error);
  


});

}

onSubmitProfile({value, valid}:{value:any, valid:boolean})
{
console.log(JSON.stringify(value));
this.registerService.updateUser(value.email,this.dashboard_data).subscribe(res =>{

    console.log(res);
    this.flashMessagesService.show('Successfully Registered!', { cssClass: 'alert-success' , timeout:3000} );
    this.router.navigate(['create-registry']);
  },
  error => {
    console.log(error);
    this.flashMessagesService.show('Failure! Invalid Credentials', { cssClass: 'alert-danger',timeout:3000 } );
    this.router.navigate(['login']);  
    console.log("failed to register");
  }

);
}

}
