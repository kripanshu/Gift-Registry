import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/login.service';
import {Credentials} from '../../models/Credentials';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
credentials:Credentials[];

email_input: string;
password_input : string;

  constructor(
      private loginService: LoginService,
      private router: Router,
      private flashMessagesService: FlashMessagesService
  ) { }
  ngOnInit() {
   // this.credentials=this.loginService.getLoginCredentials();
  }
onSubmit()
  {
  if(this.loginService.getLoginCredentials(this.email_input,this.password_input)===null)
  {
    this.flashMessagesService.show('Failure! Invalid Credentials', { cssClass: 'alert-danger',timeout:4000 } );
    this.router.navigate(['login']);  
  }
  else
  {
    console.log(this.loginService.getLoginCredentials(this.email_input,this.password_input));
    this.flashMessagesService.show('Successfully LoggedIN!', { cssClass: 'alert-success' , timeout:4000} );
    this.router.navigate(['dashboard']);
  }


  }
  



}
