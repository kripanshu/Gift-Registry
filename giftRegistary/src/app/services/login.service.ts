import { Injectable } from '@angular/core';
import {Credentials} from '../models/Credentials'
import {SharedService} from '../services/shared.service';

@Injectable()
export class LoginService {
  validUser :boolean= false;
  credentials : Credentials[];
  constructor(private sharedService:SharedService)
  {
 this.credentials=
 [
  {
    email:'kxb@utd.com',
    password:'abc123'
  },
  {
    email:'mital@utd.com',
    password:'xyz123'
  }
 ]

   }

   getLoginCredentials(email_input : string, password_input : string)
   {
     for(let i=0; i<this.credentials.length; i++ )
     {
    if(email_input===this.credentials[i].email && password_input===this.credentials[i].password)
        { console.log(" successfull credentials")
        this.sharedService.publishMessage(this.credentials[i].email);  
        return this.credentials[i];
       
        }
    else {
      console.log(" unsuccessfull credentials")
          return null;
         }
     }
   }

   addUser(first_Name:string,last_Name:string,middle_Name:string,phone:number,email:string,street:string,zipcode:number,state:string,country:string,password:string)
   {
    this.credentials.unshift({
      firstName:first_Name,
      lastName:last_Name,
      middleName:middle_Name,
      phone:phone,
      email:email,
      street:street,
      zipcode:zipcode,
      state:state,
      country:country,
      password:password
    })
   }

}
