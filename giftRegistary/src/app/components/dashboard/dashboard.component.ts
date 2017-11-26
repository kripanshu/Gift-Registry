import { Component, OnInit } from '@angular/core';
import {SharedService} from '../../services/shared.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private sharedService: SharedService) { }
  loggedInUserEmail:String;
  ngOnInit() {
    this.sharedService.message.subscribe(message => {
      this.loggedInUserEmail = message;
    });
  }

}
