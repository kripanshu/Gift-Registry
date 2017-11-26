import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data.service';
import {Question} from '../../models/Question';
@Component({
  selector: 'app-forget-passowrd',
  templateUrl: './forget-passowrd.component.html',
  styleUrls: ['./forget-passowrd.component.css']
})
export class ForgetPassowrdComponent implements OnInit {
  questions:Question[];
  
    constructor(public dataService:DataService) { 
      
    }
  
    ngOnInit() {
      this.questions = this.dataService.getQuestions();
    }
  
}
