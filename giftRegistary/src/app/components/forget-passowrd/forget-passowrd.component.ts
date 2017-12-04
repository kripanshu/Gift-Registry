import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import { RegisterService} from '../../services/register.service';
import {Question} from '../../models/Question';
import {Credentials} from '../../models/Credentials';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
import { log } from 'util';
import { LocalDataSource } from 'ng2-smart-table/lib/data-source/local/local.data-source';
import {SharedService} from '../../services/shared.service';
@Component({
  selector: 'app-forget-passowrd',
  templateUrl: './forget-passowrd.component.html',
  styleUrls: ['./forget-passowrd.component.css']
})
export class ForgetPassowrdComponent implements OnInit {
  question:string;
  answer:string;
  password:string;
  user_answer:string;
  hide:boolean=true;
  loggedInuserEmail:string;
  hideForm:boolean=true;
  showPassword:LocalDataSource;
  Username:string;

  dashboard_data:Credentials ={
    
      firstName:'',
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
    constructor(public dataService:DataService,
      private registerService:RegisterService,
      private sharedService:SharedService,
      private flashMessageServices: FlashMessagesService
    
    ) { 
      this.showPassword=new LocalDataSource();

    
      
    }
  
    ngOnInit() 
    {

      
    }

    
    
  
getForegtPasswordData(email:string)
{

  this.registerService.getUserProfileData(email).subscribe( res =>{
    
    console.log("Get user profile ",res.json().firstName);
    this.dashboard_data=res.json();
   
        this.question=this.dashboard_data.securityQuestion;
        this.Username=this.dashboard_data.firstName;
        this.answer=this.dashboard_data.securityAnswer;
        this.password=this.dashboard_data.password;
        this.flashMessageServices.show('Hey '+this.Username+' Answer this simple Question', { cssClass: 'alert-success',timeout:3000 } );

    
  },
  error => {
    console.log(error);
    
    this.flashMessageServices.show(' Enter correct Email ID', { cssClass: 'alert-danger',timeout:3000 } );
  
  });
  

}
getUrl()
{

  console.log("FOrget user email.",this.loggedInuserEmail)
  this.getForegtPasswordData(this.loggedInuserEmail);

}
checkUser()
{
if(this.answer==this.user_answer)
{
  
  console.log("asdasdasdas")
  this.showPassword.add(this.password);
this.showPassword.refresh();

}

}

}
