import { Component, OnInit } from '@angular/core';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
import {InventoryService} from '../../services/inventory.service';
import { Inventory } from '../../models/Inventory';
import { LocalDataSource } from 'ng2-smart-table';

@Component({
  selector: 'app-admin-inventory',
  templateUrl: './admin-inventory.component.html',
  styleUrls: ['./admin-inventory.component.css']
})
export class AdminInventoryComponent implements OnInit {
  
  itemId=0;
  item_Name:string;
  description:string;
  category:string;
  price:number;
  quantity:number;
  

  
addItems: Inventory;

  registryItemsSource: LocalDataSource;
  data_adminInventory:Inventory[]=[];
  
  
  
  settings_admininventory = {
    mode : 'external',
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
    delete:true,
add:true,
edit:false
        },
      
 };
 

  
  constructor(
private inventoryService: InventoryService,
private flashMessagesService: FlashMessagesService

  ) { 

    this.registryItemsSource = new LocalDataSource(this.data_adminInventory);
  }

  ngOnInit() {
this.getInventoryItems();

  }

  // =[{"itemId":1,"itemName":"Car","description":"Nice Car","category":"Vehicle","price":324.0,"quantity":5}];
getInventoryItems()
{
  this.inventoryService.getItems().subscribe(result =>{

      //console.log("the result",result.json()[0]);
      for(let i=0; i<result.json().length; i++)
      {
      this.registryItemsSource.add(result.json()[i]);
      this.registryItemsSource.refresh();
      }
     console.log("hello:",this.data_adminInventory);
    },
    error => {
      console.log(error);
       
      console.log("failed to get Items at UI");
              }
  );
}


addInventoryItems({value,valid}:{value:any,valid:boolean})
{
  
  
  // console.log("item:",this.item_Name);
  // console.log("decription:",this.description);
  // console.log("item:",this.category);
  // console.log("item:",this.price);
  // console.log("item:",this.quantity);

  this.addItems={
    
    itemId:this.itemId,
    itemName:this.item_Name,
    description:this.description,
    category:this.category,
    price:this.price,
    quantity:this.quantity
   
  };


 

console.log(this.addItems);
this.inventoryService.addItems(this.addItems).subscribe(

  result => {
    console.log(result);
    this.flashMessagesService.show('Successfully Added!', { cssClass: 'alert-success' , timeout:3000} );
    this.registryItemsSource.empty();
  
    this.getInventoryItems();
    this.registryItemsSource.refresh();
    //ngOnInit();
  },
  error => {
    console.log(error);
    this.flashMessagesService.show('Failed to add', { cssClass: 'alert-danger',timeout:3000 } );
     
    console.log("failed to add data");
  }
);

}
deleteItem(event)
{
  console.log("*****This is data we want for delete *******************",event.data);
  console.log("*****This is item ID we want for delete *******************",event.data.itemId);


  this.inventoryService.deleteItemFromInventory(event.data.itemId).subscribe(res=>{
    console.log("response after deleting item",res)
    this.flashMessagesService.show('Successfully deleted data to Inventory', { cssClass: 'alert-success' , timeout:3000} );
    this.registryItemsSource.empty();
    this.registryItemsSource.refresh();
    this.getInventoryItems();
    
    },
    error => {
      console.log(error);
      this.flashMessagesService.show('Problem in deleting Data, Try Again!', { cssClass: 'alert-danger',timeout:3000 } );
    }
    
    
    );
}

}
