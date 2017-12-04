import { Component, OnInit } from '@angular/core';
import {LoginService} from '../../services/login.service';
import {Credentials} from '../../models/Credentials';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
import {AdminCredentials}from '../../models/admin-credentials'
import { AuthService } from "angular4-social-login";
import { FacebookLoginProvider, GoogleLoginProvider } from "angular4-social-login";
// import { AuthService } from "angular4-social-login";
import { SocialUser } from "angular4-social-login";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private user: SocialUser;
  private loggedIn: boolean;
//single sign on

signInWithGoogle(): void {
  this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
}

signInWithFB(): void {
  this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
}

signOut(): void {
  this.authService.signOut();
}







credentials:Credentials[];
admin_credentials:AdminCredentials[];
role_input: any []=[
  {id:1, name:'User'},
  {id:2, name:'Admin'}
]
curAccess: any ;
email_input: string;
password_input : string;

  constructor(
      private loginService: LoginService,
      private router: Router,
      private flashMessagesService: FlashMessagesService,
      private authService:AuthService
  ) { }
  ngOnInit() {
   // this.credentials=this.loginService.getLoginCredentials();

   this.authService.authState.subscribe((user) => {
    this.user = user;
    this.loggedIn = (user != null);
  });



   this.curAccess=[{id:1, name:'User'}];
   console.log("this is type for now:",this.curAccess[0].name);
   
  }
  //to get the usertype
roleInput(id:any):void{
  console.log("the id would be ",id);
  this.curAccess =this.role_input.filter(value => value.id == parseInt(id));
  console.log("this is access type:", this.curAccess[0].name);
    
  }
onSubmit({value,valid}:{value:any,valid:boolean})
  {
//if role is admin
if(this.curAccess[0].name==="Admin")
{
 
console.log("value from form", value.email_input + " " + value.password_input);

  this.loginService.getAdminLoginCredentials(this.email_input,this.password_input).subscribe(
  
      result => {
        console.log("login successful ?")
        //console.log(result.json());
        
        this.flashMessagesService.show('Successfully LoggedIN!', { cssClass: 'alert-success' , timeout:3000} );
        this.router.navigate(['admin-inventory']);
      },
      error => {
        console.log(error);
        console.log("login successfull *")
        this.flashMessagesService.show('Failure! Invalid Credentials', { cssClass: 'alert-danger',timeout:3000 } );
        this.router.navigate(['login']);   
        console.log("failed to register");
      }
    );
}
else if(this.curAccess[0].name==="User"){

// if role is user
console.log("value from form", value.email_input + " " + value.password_input);

  this.loginService.getLoginCredentials(this.email_input,this.password_input).subscribe(
    
      result => {
        console.log("the result for login",result._body);
        
        
        this.flashMessagesService.show('Successfully LoggedIN!', { cssClass: 'alert-success' , timeout:3000} );
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
  
 id:string=this.email_input; //can be anything else
forgetPassword()
{
  console.log("forget password?");
  
}

}
