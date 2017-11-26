import { Component, OnInit } from '@angular/core';
import {RegisterService} from '../../services/register.service';
import {Credentials} from '../../models/Credentials';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
firstName_input: string;
lastName_input: string;
middleName_input: string;

email_input: string;
phone_input: number;

street_input:string;
zipcode_input:number;
state_input:string;
country_input: string;
password_input:string;


  constructor(
    private registerService: RegisterService,
    private router: Router,
    private flashMessagesService: FlashMessagesService

  ) { }

  ngOnInit() {
  }
registerNow()
{
console.log(this.firstName_input);
console.log(this.lastName_input);
console.log(this.middleName_input);
console.log(this.email_input);
console.log(this.phone_input);
console.log(this.street_input);
console.log(this.zipcode_input);
console.log(this.state_input);
console.log(this.country_input);
console.log(this.password_input);

let value=this.registerService.setCredentials(this.firstName_input,this.lastName_input,this.middleName_input,this.phone_input,this.email_input,this.street_input,this.zipcode_input,this.state_input,this.country_input,this.password_input);
console.log(value,"the value")  

if(value===null)
  {
    this.flashMessagesService.show('Failure! Invalid Credentials', { cssClass: 'alert-danger',timeout:3000 } );
    this.router.navigate(['register']);  
    console.log("failed to register");
  }
  else{
  this.flashMessagesService.show('Successfully Registered!', { cssClass: 'alert-success' , timeout:3000} );
  this.router.navigate(['dashboard']);

  }
}
}
