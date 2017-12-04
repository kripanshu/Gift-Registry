import { Component, OnInit } from '@angular/core';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router,ActivatedRoute} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
import {SharedService}from  '../../services/shared.service'
import {RegisteryService} from '../../services/registery.service';
import { Inventory } from '../../models/Inventory';
import { LocalDataSource } from 'ng2-smart-table';
import { InventoryService } from '../../services/inventory.service';
import { ItemModels } from '../../models/ItemModel';
import { personalRegistry } from '../../models/personalRegistry';
import {SharedregistryService} from '../../services/sharedregistry.service';
@Component({
  selector: 'app-user-registry',
  templateUrl: './user-registry.component.html',
  styleUrls: ['./user-registry.component.css']
})
export class UserRegistryComponent implements OnInit {
  registryItemsSources: LocalDataSource;
  inventoryItemsSources: LocalDataSource;
registryName:string='My Registry';
itemDetails:ItemModels;// model for the selfassign table

data_registry:personalRegistry[]=[];// to be displayed
inventory_registry:Inventory[]=[];
mainregistrydata:Inventory[]=[];
;
data_inventory:Inventory;
registryUrl:string;



  settings_registry = {
    mode:'external',
    columns: {
      itemName: {
        title: 'Item Name',
        sort:true,
        sortDirection:'asc'
        
      },
      description: {
        title: 'Description',
        sort:true,
        sortDirection:'asc'
      },
      category: {
        title: 'Category',
        sort:true,
        sortDirection:'asc'
      },
      price: {
        title: 'Price',
        sort:true,
        sortDirection:'asc'
      },
      quantity: {
        title: 'Quantity',
        sort:true,
        sortDirection:'asc'
      },
      taken : {
        title:'Taken',
        sort:true,
        sortDirection:'asc'
      },
      email : {
        title:'Email',
        sort:true,
        sortDirection:'asc'
      }


    },
  actions: {
    delete:true,
add:false,
edit:false
        },
      
 };
 


  settings_inventory = {
    mode:'external',
    columns: {
      itemName: {
        title: 'Item Name',
        sort:true,
        sortDirection:'asc'
        
      },
      description: {
        title: 'Description',
        sort:true,
        sortDirection:'asc'
      },
      category: {
        title: 'Category',
        sort:true,
        sortDirection:'asc'
      },
      price: {
        title: 'Price',
        sort:true,
        sortDirection:'asc'
      },
      quantity: {
        title: 'Quantity',
        sort:true,
        sortDirection:'asc'
      }
    },
  actions: {
    delete:false,
add:false,
edit:false
        },
      };
 
      loggedInUserEmail:string;
      loggedInUserName:string;
 
  constructor(
private ng2SmartTableModule:Ng2SmartTableModule,
private flashMessagesService:FlashMessagesService,
private router:Router,
private sharedService: SharedService,
private activatedRoute:ActivatedRoute,
private registeryService:RegisteryService,
private inventoryServices:InventoryService,
private sharedregistryService: SharedregistryService

  ) {

    this.registryItemsSources = new LocalDataSource(this.data_registry);
    this.inventoryItemsSources = new LocalDataSource(this.inventory_registry);

   }

  ngOnInit() {
    this.sharedService.message.subscribe(message => {
      this.loggedInUserEmail=message;
      console.log(this.loggedInUserEmail);
      
      if(this.loggedInUserEmail=='')
      {
        this.router.navigate(['login']);
  
      }
  
      
  });
 
    this.registryUrl=this.activatedRoute.snapshot.params['regurl'];
    //console.log("** my regurl",this.registryUrl);

    if(this.registryUrl=='')
    {
      this.router.navigate(['create-registry']);

    }

    this.data_registry=[];
    this.mainregistrydata=[];
    this.getNowInventoryItems();
    console.log("we call get item")
    this.getNowRegistryItems(this.registryUrl);
    
  
   
  }
  

getNowRegistryItems(url:string)
{
  // console.log("TEST: We have called to get registry items")
  // console.log("TEST: URLcalled to get registry items",url)
this.registeryService.getRegistryItems(url).subscribe(res=>{

  //this.mainregistrydata=[];
  console.log("First Part of data",res);
  for(let i=0; i<res.json().length;i++){
    console.log("count the data",i);
    
    //this.mainregistrydata.push(res.json()[i]);
  
      //this.data_registryhalf[i]=res.json()[i];
this.mainregistrydata.push({
 itemId:res.json()[i].itemId,
itemName:res.json()[i].itemName,
description:res.json()[i].description,
category:res.json()[i].category,
price:res.json()[i].price,
quantity:res.json()[i].quantity,
})

  }
  


  console.log("we call assign item now")
  this.getItemAssignData(this.registryUrl);
},
error => {
  console.log(error);
  
  //console.log("WARNING !!!!!!!!!!!!!! FAILED TO GET REGISTRY NAME");
}

);
  
}



getItemAssignData(regUrl)
{ console.log("getItemAssign called")
  
  let url:string=regUrl;
this.sharedregistryService.getUpdatedItems(url).subscribe(res=>{
  console.log("Success !!!!!!!!!!")
console.log("Second Part of the data",res.json()[0]);
console.log("TEST : getNowRegistryItemshalf hello:",this.mainregistrydata[0]);
  for(let i=0; i<res.json().length;i++){
//     for(let j=0; j<this.mainregistrydata.length;j++){

//       console.log("both items:",res.json()[i].itemId," and ", this.mainregistrydata[j].itemId);
// if(res.json()[i].itemId==this.mainregistrydata[j].itemId){
// this.registryItemsSources.empty();
  console.log("item found", res.json()[i].itemId);
this.registryItemsSources.add({
  itemId:this.mainregistrydata[i].itemId,
  itemName:this.mainregistrydata[i].itemName,
  description:this.mainregistrydata[i].description,
  category:this.mainregistrydata[i].category,
  price:this.mainregistrydata[i].price,
  quantity:this.mainregistrydata[i].quantity,
  taken:res.json()[i].taken,
  email:res.json()[i].email

});


 }
 this.registryItemsSources.refresh();
// this.registryItemsSources.refresh();
console.log("THe data to be printed has the following",this.data_registry);

//this.data_registry.splice(-1,1);
// this.registryItemsSources.add(this.data_registry);  

console.log("much needed in UI",this.registryItemsSources);
});

}




getNowInventoryItems()
{
this.inventoryServices.getItems().subscribe(res=>{


  //console.log(res);
  for(let i=0; i<res.json().length;i++){
  this.inventoryItemsSources.add(res.json()[i]);
  this.inventoryItemsSources.refresh();
  }
  //console.log("hello:",this.inventory_registry);
},
error => {
  console.log(error);
  
  //console.log("failed to create registery");
}

);
  
}

deleteItem(event)
{
  console.log("delete event called")
  console.log(event.data);

  console.log("TEST: url sent to delete item",this.registryUrl);
  this.registeryService.deleteItemFromRegistry(this.registryUrl,event.data.itemId).subscribe(res=>{
    console.log("response after adding item",res)
    this.flashMessagesService.show('Successfully deleted data to Registry', { cssClass: 'alert-success' , timeout:3000} );
    this.registryItemsSources.empty();
    this.registryItemsSources.refresh();
     this.getNowRegistryItems(this.registryUrl);
    
    },
    error => {
      console.log(error);
      this.flashMessagesService.show('Problem in deleting Data, Try Again!', { cssClass: 'alert-danger',timeout:3000 } );
    }
    
    
    );
    
}
addItem(event){
console.log("the data coming :::::::::::::")
  console.log(event.data);
  console.log("TEST: url sent to add item",this.registryUrl);
this.itemDetails=
{
  registryUrl:this.registryUrl,
  itemId:event.data.itemId,
  quantity:event.data.quantity,
  taken:0
  
  

};
console.log("Test : data sent to add Item", this.itemDetails);
 // console.log("Append This : ",this.registryUrl );
this.registeryService.addItemToRegistry(this.itemDetails).subscribe(res=>{
console.log("response after adding item",res)
this.flashMessagesService.show('Successfully Added data to Registry', { cssClass: 'alert-success' , timeout:3000} );
this.registryItemsSources.empty();
this.registryItemsSources.refresh();
this.getNowRegistryItems(this.registryUrl);
this.registryItemsSources.refresh();
},
error => {
  console.log(error);
  this.flashMessagesService.show('Problem in adding Data, Try Again!', { cssClass: 'alert-danger',timeout:3000 } );
}


);

}

  visitInventory()
  {
    this.flashMessagesService.show('Welcome to Inventory', { cssClass: 'alert-success' , timeout:3000} );
    this.router.navigate(['user-inventory']);  
  }

}
