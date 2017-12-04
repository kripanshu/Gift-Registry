import { Component, OnInit } from '@angular/core';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router,ActivatedRoute} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
import {RegisteryService} from '../../services/registery.service';
import {SharedService} from '../../services/shared.service';
import {SharedregistryService} from '../../services/sharedregistry.service';
import { Inventory } from '../../models/Inventory';
import { LocalDataSource } from 'ng2-smart-table';
import { InventoryService } from '../../services/inventory.service';
import { ItemModels } from '../../models/ItemModel';

@Component({
  selector: 'app-sharedregistry',
  templateUrl: './sharedregistry.component.html',
  styleUrls: ['./sharedregistry.component.css']
})
export class SharedregistryComponent implements OnInit {
access:string;
regurl:string;
registryItemsSources: LocalDataSource;
data_registry:ItemModels[]=[];
registryUrl:string;
selfassigndata:ItemModels={

  registryUrl:'',
  itemId:0,
  quantity:0,
  taken:0,
  email:''
};
  constructor(
    private ng2SmartTableModule:Ng2SmartTableModule,
    private flashMessagesService:FlashMessagesService,
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private registeryService:RegisteryService,
    private inventoryServices:InventoryService,
    private sharedService : SharedService,
    private sharedregistryService: SharedregistryService
  ) {

    this.registryItemsSources = new LocalDataSource(this.data_registry);
   }
   loggedInUserEmail:string;

  ngOnInit() {
    
    this.sharedService.message.subscribe(message => {
      this.loggedInUserEmail=message;
      console.log(this.loggedInUserEmail);
    });
    if(this.loggedInUserEmail=='')
    {
      this.router.navigate(['create-registry']);

    }

    this.registryUrl=this.activatedRoute.snapshot.params['regurl'];
    this.regurl=this.registryUrl;
    console.log("** shared my regurl",this.registryUrl);
    this.getNowRegistryItems(this.registryUrl);
    
  
  }


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
     

    },
  actions: {
    delete:false,
add:false,
edit:false
        },
      
 };

 

  //get registry Items
  getNowRegistryItems(url:string)
  {
  this.registeryService.getRegistryItems(url).subscribe(res=>{
  
  
    console.log(res);
    for(let i=0; i<res.json().length;i++){
    this.registryItemsSources.add(res.json()[i]);
    this.registryItemsSources.refresh();
    }
    console.log("hello this is shared registry section:",this.data_registry);
  },
  error => {
    console.log(error);
    
    console.log("failed to create registery");
  }
  
  );
    
  }

  assignMe(event)
  {
//console.log(event.data);
        this.selfassigndata=
        {
          registryUrl:this.registryUrl,
          itemId:event.data.itemId,
          quantity:event.data.quantity,
          taken:1,
          email:this.loggedInUserEmail

        }

console.log("Item assigned",this.selfassigndata)
        
        this.sharedregistryService.selfAssign(this.loggedInUserEmail,this.selfassigndata).subscribe(res=>{
          console.log("The result after assigning item",res);

          this.flashMessagesService.show('You have assigned '+event.data.itemName+' item', { cssClass: 'alert-success',timeout:3000 } );
          
          },
          error => {
            console.log(error);
            this.flashMessagesService.show('Problem in assigning Data, Try Again!', { cssClass: 'alert-danger',timeout:3000 } );
          
          

        });

  }

}
