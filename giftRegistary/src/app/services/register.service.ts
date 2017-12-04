import { Injectable } from '@angular/core';
import {Credentials} from '../models/Credentials'
import {SharedService} from '../services/shared.service';
import {LoginService} from '../services/login.service';
import {NamesharedService} from '../services/nameshared.service';
import {Http,Response} from '@angular/http';
import {Headers,RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Rx';
@Injectable()
export class RegisterService {
 
  register_credentials : Credentials;
  validUser:boolean=true;
  constructor(
    private sharedService:SharedService,
    private loginService:LoginService,
    private nameSharedService:NamesharedService,
    private http:Http
  ) { }

setCredentials(input : Credentials)
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
  firstName:input.firstName,
  lastName:input.lastName,
  email:input.email,
  street:input.street,
  state:input.state,
  zipcode:input.zipcode,
  country:input.country,
  securityQuestion:input.securityQuestion,
  securityAnswer:input.securityAnswer,
  password:input.password
  
};

//console.log(" input register is "+ this.register_credentials);
//code for linking to database
let body = JSON.stringify(this.register_credentials);
console.log(" body to send at backend"+ body);

let headers = new Headers();

headers.append('Content-Type','application/json');
let options = new RequestOptions({headers : headers});

return this.http.post('http://localhost:8080/Wpl/user/add/',body,options).map(this.extractData).catch(this.handleError);



//now send it the database
//if invalid user(already exist) validUser=false;
// this.loginService.addUser(this.register_credentials);
  
// if(this.validUser)
// {
//   console.log(" successfull register credentials");
// this.sharedService.publishMessage(input.email);
// this.nameSharedService.publishMessage(input.firstName+" "+input.lastName);

//   return input.firstName;
// }else
// {
//   console.log(" unsuccessfull register credentials");
//   return null;
// }
 }
updateUser(email :string, data:Credentials)
{
  // this.register_credentials={
  //   firstName:data.firstName,
  //   lastName:data.lastName,
  //   email:data.email,
  //   street:data.street,
  //   state:data.state,
  //   zipcode:data.zipcode,
  //   country:data.country,
  //   securityQuestion:data.securityQuestion,
  //   securityAnswer:data.securityAnswer,
  //   password:data.password

  // }
  
  console.log("Our incoming data",this.register_credentials)
  let body = JSON.stringify(data);
  console.log(" body to send at backend to UPDATE"+ body);
  
  let headers = new Headers();
  
  headers.append('Content-Type','application/json');
   
   let options = new RequestOptions({
    params:
    {
      email:email,
    
    },
    headers : headers
        });
  
  return this.http.put('http://localhost:8080/Wpl/user/updateuser/',body,options).map(this.extractData).catch(this.handleError);
  
}
getUserProfileData(email:string)
{
  let options = new RequestOptions({
    params:
    {
      email:email,
    
    }
        });

        console.log("options",options);

    return this.http.get('http://localhost:8080/Wpl/user/getuser/',options).map(res=>
    {
      console.log(res);
    return res;

    }).catch(this.handleError);
}

 private extractData (res :Response)
 {
   console.log(res);
   return res.statusText || {};
 }

 private handleError(error: Response)
 {
   console.log(error);
   return Observable.throw("Email already exist");
 }



}
