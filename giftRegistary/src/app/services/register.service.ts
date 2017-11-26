import { Injectable } from '@angular/core';
import {Credentials} from '../models/Credentials'
import {SharedService} from '../services/shared.service';
import {LoginService} from '../services/login.service';

@Injectable()
export class RegisterService {
 
  register_credentials : Credentials;
  validUser:boolean=true;
  constructor(
    private sharedService:SharedService,
    private loginService:LoginService

  ) { }

setCredentials(first_Name:string,last_Name:string,middle_Name:string,phone:number,email:string,street:string,zipcode:number,state:string,country:string,password:string)
{
//console.log("firstname",this.register_credentials.firstName);
// this.loginService.credentials.push({
// firstName:first_Name,
// lastName:last_Name,
// middleName:middle_Name,
// phone:phone,
// email:email,
// street:street,
// zipcode:zipcode,
// state:state,
// country:country,
// password:password
// });

this.register_credentials={
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

};
//now send it the database
//if invalid user(already exist) validUser=false;
this.loginService.addUser(this.register_credentials.firstName,this.register_credentials.lastName,this.register_credentials.middleName,this.register_credentials.phone,this.register_credentials.email,this.register_credentials.street,this.register_credentials.zipcode,this.register_credentials.state,this.register_credentials.country,this.register_credentials.password);

if(this.validUser)
{
  console.log(" successfull register credentials");
this.sharedService.publishMessage(this.register_credentials.email);

  return this.register_credentials.firstName;
}else
{
  console.log(" unsuccessfull register credentials");
  return null;
}
}

}
