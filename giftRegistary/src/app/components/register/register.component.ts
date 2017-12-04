import { Component, OnInit } from '@angular/core';
import {RegisterService} from '../../services/register.service';
import {Credentials} from '../../models/Credentials';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
import { log } from 'util';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


registration_data:Credentials ={

  firstName: '',
  lastName: '',
  email: '',
  securityQuestion:'',
  securityAnswer:'',
  password:'',
  street:'',
  state:'',
  zipcode:'',
  country:''



};

  constructor(
    private registerService: RegisterService,
    private router: Router,
    private flashMessagesService: FlashMessagesService

  ) { }

  ngOnInit() {


  }
registerNow({value,valid}:{value:Credentials, valid:boolean})
{
  // this.registration_data={
    
  //   firstName:this.firstName_input,
  //   lastName:this.lastName_input,
  //   email:this.email_input,
  //   street:this.street_input,
  //   state:this.state_input,
  //   zipcode:this.zipcode_input,
  //   country:this.country_input,
  //   securityQuestion:this.security_Question,
  //   securityAnswer:this.security_Answer,
  //   password:this.password_input
    
      
  //   };
console.log("data sent to setCredentials", this.registration_data);

this.registerService.setCredentials(this.registration_data).subscribe(

  result => {
    console.log(result);
    this.flashMessagesService.show('Successfully Registered!', { cssClass: 'alert-success' , timeout:3000} );
    this.router.navigate(['create-registry']);
  },
  error => {
    console.log(error);
    this.flashMessagesService.show('Failure! Invalid Credentials', { cssClass: 'alert-danger',timeout:3000 } );
    this.router.navigate(['register']);  
    console.log("failed to register");
  }
);
//console.log(value,"the value")  


}
}
