import { Injectable } from '@angular/core';
import {CreateRegistry} from '../models/CreateRegistry';
import {Http,Response} from '@angular/http';
import {Headers,RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import { ItemModels } from '../models/ItemModel';

@Injectable()
export class SharedregistryService {

  constructor(
    private http:Http
  ) { }


selfAssign(email:string,data:ItemModels)
{
  //console.log("Our incoming data",this.register_credentials)
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
  
  return this.http.put('http://localhost:8080/Wpl/item/selfassign/',body,options).map(this.extractData).catch(this.handleError);
  
}

getUpdatedItems(url:string)
{
  let options = new RequestOptions({
    params:
    {
      registryUrl:url,
    
    }
        });

        console.log("options",options);

    return this.http.get('http://localhost:8080/Wpl/item/registryitemdetail/',options).map(res=>
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
