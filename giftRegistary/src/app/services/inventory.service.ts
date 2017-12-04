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
export class InventoryService {

  constructor(
    private http:Http
  ) { }

getItems()
{

  let options = new RequestOptions({
    params:
    {
      
    }
        });

        console.log("options",options);

    return this.http.get('https://localhost:9090/Wpl/inventory/inventorylist/',options).map(res=>
    {
      console.log("we get items in registry")
    return res;

    }).catch(this.handleError);



}

addItems(data:any)
{
  let body = JSON.stringify(data);
  console.log(" body to send at backend"+ body);
  
  let headers = new Headers();
  
  headers.append('Content-Type','application/json');
  let options = new RequestOptions({headers : headers});
  
  return this.http.post('https://localhost:9090/Wpl/inventory/add/',body,options).map(this.extractData).catch(this.handleError1);
  
  

}

deleteItemFromInventory(itemId:number)
{
//console.log("userEmail sent to get registries",registryUrl)
  console.log("item ID sent to server : ",itemId)

  


  let options = new RequestOptions({ 
    params:{
    
    'itemId':itemId
    }
    });

    console.log("delete params",itemId)
    return this.http.delete('http://localhost:8080/Wpl/inventory/deleteinventory/',options)
    .map(res => {
    return res;
    })
    .catch(this.handleError);
  

}


private handleError(error: Response)
{
  console.log(error);
  alert(error);
  return Observable.throw("Can not get items into Inventory");
}
private extractData (res :Response)
{
  console.log(res);
  return res.statusText || {};
}

private handleError1(error: Response)
{
  console.log(error);
  return Observable.throw("Adding Item failed");
}


}
