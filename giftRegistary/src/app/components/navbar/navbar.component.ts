import { Component, OnInit } from '@angular/core';
import {SharedService} from '../../services/shared.service';
import {NamesharedService} from '../../services/nameshared.service';
import { FlashMessagesService } from 'angular2-flash-messages';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  loggedInUserName:String='';
  userIs: boolean=false;
  constructor(
private nameSharedService:NamesharedService,
private sharedService: SharedService,
private flashMessagesService:FlashMessagesService
  ) { }

  ngOnInit() {

    this.nameSharedService.namemessage.subscribe(message => {
      this.loggedInUserName = message;

      if(this.loggedInUserName!= '')
      {
        console.log("This is user", this.loggedInUserName )
        this.userIs=true;
      }
      else
       {
         this.userIs=false;
      }
  
  });

}
logout()
{
  this.sharedService.publishMessage('');  
  this.nameSharedService.publishMessage('');
  this.flashMessagesService.show('Successfully Logged Out!', { cssClass: 'alert-success' , timeout:4000} );
  
}

}
