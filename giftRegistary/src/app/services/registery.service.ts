import { Injectable } from '@angular/core';
import {CreateRegistry} from '../models/CreateRegistry';
import {Http,Response} from '@angular/http';
import {Headers,RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class RegisteryService {
createRegistry: CreateRegistry[];
registrycreated:boolean=true;
  constructor(


private http:Http

  ) 
  {
this.createRegistry=[{
  registryID:0,
  registryName:'',
  registryUrl:'',
  share:1,
  userEmail:'',
  eventDate:""
  //event date not set

}]

   }
   accessUserRegistry(email:string)
  {
    console.log("userEmail sent to get registries",email)
    
    let options = new RequestOptions({ 
      params:{
      'userEmail': email
      }
      });
      return this.http.get('http://localhost:8080/Wpl/registry/registrylist/',options)
      .map(res => {
      return res;
      })
      .catch(this.handleError1);
      
  }
  accessSharedUserRegistry(email:string)
  {
    console.log("userEmail sent to get registries",email)
    
    let options = new RequestOptions({ 
      params:{
      'email': email
      }
      });
      return this.http.get('http://localhost:8080/Wpl/sharedregistry/registrylist/',options)
      .map(res => {

        console.log("Shared Registries",res)
      return res;
      })
      .catch(this.handleError1);
      
  }
sendRegistryData(input_details:CreateRegistry)
{
  console.log("this is sent to registry table ********",input_details)

  //this.createRegistry.unshift(input_details);
  let body = JSON.stringify(input_details);
  console.log(" body to send at backend"+ body);
  
  let headers = new Headers();
  
  headers.append('Content-Type','application/json');
  let options = new RequestOptions({headers : headers});
  
  return this.http.post('http://localhost:8080/Wpl/registry/add/',body,options).map(this.extractData).catch(this.handleError);



}
// get items for particular registry
getRegistryItems(registryUrl:string)
{
  console.log("TEST : : : userEmail sent to get registries ",registryUrl)
  
  let options = new RequestOptions({ 
    params:{
    'registryUrl': registryUrl
    }
    });
    return this.http.get('http://localhost:8080/Wpl/item/registryitems/',options)
    .map(res => {
    return res;
    })
    .catch(this.handleError2);
    
}

addItemToRegistry(data:any)
{

  let body = JSON.stringify(data);
  console.log(" body to send at backend"+ body);
  
  let headers = new Headers();
  
  headers.append('Content-Type','application/json');
  let options = new RequestOptions({headers : headers});
  
  return this.http.post('http://localhost:8080/Wpl/item/add/',body,options).map(this.extractData).catch(this.handleError);
  
  
  
}
deleteItemFromRegistry(registryUrl:string, itemID:number)
{

  //console.log("userEmail sent to get registries",registryUrl)
  
  let options = new RequestOptions({ 
    params:{
    'registryUrl': registryUrl,
    'itemId':itemID
    }
    });

    console.log("delete params",registryUrl,itemID)
    return this.http.delete('http://localhost:8080/Wpl/item/deleteitem/',options)
    .map(res => {
    return res;
    })
    .catch(this.handleError2);
    

}

shareRegistryWith(data)
{
  let body = JSON.stringify(data);
  console.log(" body to send at backend"+ body);
  
  let headers = new Headers();
  
  headers.append('Content-Type','application/json');
  let options = new RequestOptions({headers : headers});
  
  return this.http.post('http://localhost:8080/Wpl/sharedregistry/add/',body,options).map(this.extractData).catch(this.handleError);
  
  

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

private handleError1(error: Response)
{
  console.log(error);
  return Observable.throw("Registry cannot be accessed");
}
private handleError2(error: Response)
{
  console.log(error);
  return Observable.throw("Registry cannot be accessed");
}
}
