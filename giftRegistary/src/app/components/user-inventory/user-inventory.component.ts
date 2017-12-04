import { Component, OnInit } from '@angular/core';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
@Component({
  selector: 'app-user-inventory',
  templateUrl: './user-inventory.component.html',
  styleUrls: ['./user-inventory.component.css']
})
export class UserInventoryComponent implements OnInit {
  settings_inventory = {
    mode:external,
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
add:true,
edit:false
        },
      
 };
 

  data_inventory = [
    {
      itemName: 'ZBow and Arrow',
      description: "ZSuper cool stuff, mast",
      price: 1170,
      quantity: 1
      
    },
    {
      itemName: 'Dekstop',
      description: "Super cool stuff, bada wala TV",
      price: 270,
      quantity: 1
    },
    {
      itemName: 'Shoes',
      description: "Super cool stuff, Adiddas",
      price: 100,
      quantity: 2
    },
    {
      itemName: 'Ephone 8',
      description: "Super cool stuff, mehnga phone",
      price: 870,
      quantity: 1
    },
    {
      itemName: 'Vphone 8',
      description: "quper cool stuff, mehnga phone",
      price: 870,
      quantity: 3
    },
    {
      itemName: 'Bbbne 8',
      description: "quper cool stuff, mehnga phone",
      price: 870,
      quantity: 11
    },
    
    // ... list of items
    
    {
      itemName: 'Iphone 8',
      description: "Super cool stuff, mehnga phone",
      price: 870,
      quantity: 1
    }
  ];
 
  constructor(
    private flashMessagesService: FlashMessagesService,
    private router:Router
  ) { }

  ngOnInit() {

    
  }
  visitRegistry()
  {
    this.flashMessagesService.show('Welcome to Registry', { cssClass: 'alert-success' , timeout:3000} );
    this.router.navigate(['user-registry']);  
  }
}
