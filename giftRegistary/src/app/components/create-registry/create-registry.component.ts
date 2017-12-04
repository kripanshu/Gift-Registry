import { Component, OnInit } from '@angular/core';
import {SharedService} from '../../services/shared.service';
import {RegisteryService} from '../../services/registery.service';
import {CreateRegistry} from '../../models/CreateRegistry';
import { FlashMessagesService } from 'angular2-flash-messages';
import {Router} from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { timeout } from 'q';
import {NamesharedService} from '../../services/nameshared.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { LocalDataSource } from 'ng2-smart-table/lib/data-source/local/local.data-source';

@Component({
  selector: 'app-create-registry',
  templateUrl: './create-registry.component.html',
  styleUrls: ['./create-registry.component.css']
})
export class CreateRegistryComponent implements OnInit {
  hide:boolean=true;
  buttonStatus:boolean=false;
  shareRegistryEmail:string;
preurl:string='blaureg-';
registry_date:string;
registry_url:string;
registry_name:string;

sendRegistryUrl:string;
privateregistries:LocalDataSource;
sharedregistries:LocalDataSource;
// registry_id=0;
// registry_date:string;
access_input: any []=[
  {id:1, name:'Public'},
  {id:0, name:'Private'}
]
curAccess: any=this.access_input[0];

model:any;

setDate(model)
{

this.registry_date=this.model.year+"-"+this.model.month+"-"+this.model.day;

}

share:number=0;
outputdata:CreateRegistry;

PrivateRegistries=[];
PrivateRegistriesUrl=[];

PrivateSharedRegistries=[];
PrivateSharedRegistriesUrl=[];


  constructor(
    private sharedService: SharedService,
    private registryService: RegisteryService,
    private nameSharedService: NamesharedService,
    private router: Router,
    private flashMessagesService: FlashMessagesService
  ) { 

    this.privateregistries= new LocalDataSource();
    this.sharedregistries=new LocalDataSource();
  }
  loggedInUserEmail:string;
  loggedInUserName:string;

  ngOnInit() {
    this.sharedService.message.subscribe(message => {
      this.loggedInUserEmail=message;
      console.log(this.loggedInUserEmail);
      
      if(this.loggedInUserEmail=='')
      {
        this.router.navigate(['login']);
  
      }
  
      
  });
 
  this.nameSharedService.namemessage.subscribe(message =>{
    this.loggedInUserName= message;
    console.log(this.loggedInUserName);
  });
  
  // console.log("this is user email:"+this.loggedInUserEmail);
  // console.log("this is user email:"+message);
  this.getUserRegistriesList(this.loggedInUserEmail);
  this.getUserSharedRegistriesList(this.loggedInUserEmail);
}

//to get the access
accessInput(id:any):void{
//console.log("the id would be ",id);
this.curAccess =this.access_input.filter(value => value.id == parseInt(id));

if(this.curAccess[0].name == "Public")
{
  
this.share=this.curAccess[0].id;
}
else if(this.curAccess[0].name == "Private")
{
  
  this.share=this.curAccess[0].id;
}

console.log("this is access type:", this.curAccess[0].name);
console.log("this is access type:", this.curAccess[0].id);
  
}



 setCredentials({value,valid}:{value:any,valid:boolean})
 {
  
   this.setDate(this.registry_date);

   console.log(this.registry_date);
   let fullurl:string;
   //console.log(Date,this.model);
   fullurl=this.preurl+this.registry_url;
  // console.log("the full url",fullurl);
this.outputdata={
  registryID:0,
  registryName:this.registry_name,
  registryUrl:fullurl,
  userEmail:this.loggedInUserEmail,
  share:this.share,
  eventDate:this.registry_date
  


};

console.log("TEST:the created registry sent is : ",this.outputdata);

this.registryService.sendRegistryData(this.outputdata).subscribe(
  
    result => {
      console.log(result);
      this.flashMessagesService.show(this.registry_name+' Successfully Created !', { cssClass: 'alert-success' , timeout:3000} );
      this.router.navigate(['user-registry/'+this.registry_url]);
    },
    error => {
      console.log(error);
      this.flashMessagesService.show('Failure! to create Registry', { cssClass: 'alert-danger',timeout:3000 } );
      this.router.navigate(['create-registry']);  
      console.log("failed to create registery");
    }
  );

  
}
getUserSharedRegistriesList(useremail:string){
  //console.log("useremail sent to services",useremail)
  this.registryService.accessSharedUserRegistry(useremail).subscribe(  
    result => {
     this.PrivateSharedRegistriesUrl=[];
       for(let i=0; i<result.json().length;i++){
        this.PrivateSharedRegistriesUrl.push(result.json()[i].registryUrl);
        console.log("TEST ::::::: privateshared registries URL:", this.PrivateRegistriesUrl[i]);
          // this.sharedregistries.add(result.json()[i].registryUrl);
          // this.sharedregistries.refresh();
         
       }

    },
    error => {
      console.log(error);
    
      console.log("failed to get shared registries");
    }

  );
// discuss later



}
getUserRegistriesList(useremail:string){

  console.log("useremail sent to services",useremail)
  this.registryService.accessUserRegistry(useremail).subscribe(  
    result => {

      console.log("the result user *********",result);
      this.PrivateRegistries=[];
      this.PrivateRegistriesUrl=[];
      
       for(let i=0; i<result.json().length;i++){

        this.PrivateRegistries.push(result.json()[i].registryName) ;
        this.PrivateRegistriesUrl.push(result.json()[i].registryUrl);
      //console.log(this.)
       }
    },
    error => {
      console.log(error);
      
      
      console.log("failed to get registries");
    }

  );
// discuss later



}



navigateOnPrivateRegistryList(regName)
{
  let url_val='';
for(let i=0;i<this.PrivateRegistries.length; i++){
  if(regName==this.PrivateRegistries[i])
  {
    //console.log("regName is :",regName);
    //console.log("stored reg url is:",this.PrivateRegistriesUrl[i]);
    //publish user registry name
    //this.flashMessagesService.show(regName+' Successfully Created !', { cssClass: 'alert-success' , timeout:3000} );
   //this.router.navigate(['user-registry']);
      url_val=this.PrivateRegistriesUrl[i];
      console.log("this is ::::::::",url_val)

      
  }
  
}
console.log("The url sent",url_val);
this.flashMessagesService.show(regName+' : Your Registry!', { cssClass: 'alert-success' , timeout:3000} );
this.router.navigate(['user-registry/'+url_val]);
}
navigateOnSharedRegistryList(regName1)
{
  let url_val='';

 console.log("navigateOnSharedRegistryList method called")
  for(let i=0;i<this.PrivateSharedRegistriesUrl.length; i++){
    console.log("regname1:",regName1)
    console.log("privatesharedRegistry",this.PrivateSharedRegistriesUrl[i]);
    if(regName1==this.PrivateSharedRegistriesUrl[i])
    {
     // console.log("regName is :",regName1);
    //  console.log("stored reg url is:",this.PrivateSharedRegistriesUrl[i]);
      //publish user registry name
      //this.flashMessagesService.show(regName+' Successfully Created !', { cssClass: 'alert-success' , timeout:3000} );
     //this.router.navigate(['user-registry']);
        url_val=this.PrivateSharedRegistriesUrl[i];
        console.log("TEST THIS WITH URL NAME :  :   :   :  boooyeah",url_val);
        
    }
    
}
this.flashMessagesService.show(url_val+' : Your Registry!', { cssClass: 'alert-success' , timeout:3000} );
this.router.navigate(['shared-registry/'+url_val]);  
}

//  to share registry 
shareRegistry()
{
  let data:any;
  data={
   'email': this.shareRegistryEmail,
  'registryUrl':this.sendRegistryUrl
  };
this.registryService.shareRegistryWith(data).subscribe(res=>{
 console.log(res);
},
error => {
  console.log(error);
  
  
  console.log("failed to get registries");

});
  

}

getRegistryUrlFromList(arr1)
{ let url_val='';
   
for(let i=0;i<this.PrivateRegistries.length; i++){
  if(arr1==this.PrivateRegistries[i])
  {
    //console.log("regName is :",regName);
    //console.log("stored reg url is:",this.PrivateRegistriesUrl[i]);
    //publish user registry name
    //this.flashMessagesService.show(regName+' Successfully Created !', { cssClass: 'alert-success' , timeout:3000} );
   //this.router.navigate(['user-registry']);
      url_val=this.PrivateRegistriesUrl[i];
      console.log("this is ::::::::",url_val)

      
  }
  console.log("TEST : The url sent",url_val);
  
}
    
this.sendRegistryUrl=url_val;
console.log("the name of the registry is bla bla : ", this.sendRegistryUrl)
}

}



