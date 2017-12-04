import { Injectable } from '@angular/core';
import {Credentials} from '../models/Credentials'
import {SharedService} from '../services/shared.service';
import {NamesharedService} from '../services/nameshared.service';
import {AdminCredentials} from '../models/admin-credentials';
import {Http,Response} from '@angular/http';
import {Headers,RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import { Request } from '@angular/http/src/static_request';

@Injectable()
export class LoginService {

  validUser :boolean= false;
  credentials : Credentials; // this is used in user profile
  admin_credentials: AdminCredentials[];

  email_general:string;
  password_input:string;
  constructor(
    private sharedService:SharedService,
    private nameSharedService : NamesharedService,
    private http:Http
  )
  {
//  this.credentials=
//  [
//   {
//     firstName:'Kripanshu',
//     lastName:'Bhargava',
//     email:'kxb@utd.com',
//     street:'7575frankfordroad',
//     state:'texas',
//     zipcode:"7575",
//     country:'US',
//     password:'abc123',
//     securityQuestion:'what is your favorite color?',
//     securityAnswer:'blue'
   
//   },
//   {
//     firstName:'Mital',
//     lastName:'Modha',
//     street:'7575frankfordroad',
//     state:'texas',
//     zipcode:"7575",
//     country:'US',
//     email:'msm@utd.com',
//    password:'xyz123',
//    securityQuestion:'your pet name?',
//    securityAnswer:'choco'
//   }
//  ]
 this.admin_credentials=[
   {
    adminname:'We dem boyz',
    adminemail:'v@s.com',
    adminpassword:'bcmc'

   },
   {
    adminname:'We dem gurlz',
    adminemail:'x@s.com',
    adminpassword:'bcbc'

   }
  ]


   }
//user
   getLoginCredentials(email_input : string, password_input : string)
   {
    this.sharedService.publishMessage(email_input);
    //console.log(email_input+" and "+password_input );

    
          let options = new RequestOptions({
      params:
      {
        email:email_input,
        password:password_input
      }
          });

          console.log("options",options);

      return this.http.get('https://localhost:9090/Wpl/user/authuser/',options).map(res=>
      {
        this.validUser=true;
        // this.validUser=true;
        this.email_general=email_input;
        
        this.checkUser();
      return res;

      }).catch(this.handleError);


   }


//admin
   getAdminLoginCredentials(email_input : string, password_input : string)
   {
     console.log("get this admin data",email_input,password_input)
    this.sharedService.publishMessage(email_input);

     let val:any;
    let options = new RequestOptions({
      params:
      {
        'email':email_input,
        'password':password_input
      }
           });
      
      return this.http.get('https://localhost:9090/Wpl/admin/authadmin/',options).map(res=>
      {
        val=res;
       
        
        
      return res;
    
      
      }).catch(this.handleError);
      
      
   }


   //to get user details

   checkUser(){
     console.log("checkUser called")
    if(this.validUser){
      
        this.addUser(this.email_general).subscribe(
          
            result => {
              
              
              this.credentials=result.json();
              console.log("REcredentials:"+result.json().email);
             // console.log("result obdy",result._body.firstName);
              this.sharedService.publishMessage(result.json().email);
              this.nameSharedService.publishMessage(" "+result.json().firstName+" "+result.json().lastName);
            },
            error => {
              console.log(error);
             
            }
          );
        
      }
   }

// get user details
   addUser(email:string)
   {console.log("adduser called")
    
    let options = new RequestOptions({
      params:
      {
        email:email,
      
      }
          });

          console.log("options",options);

      return this.http.get('https://localhost:9090/Wpl/user/getuser/',options).map(res=>
      {
        console.log(res);
      return res;

      }).catch(this.handleError);

   }

   private handleError(error: Response)
   {
     console.log(error);
     return Observable.throw("Invalid Email");
   }

}
