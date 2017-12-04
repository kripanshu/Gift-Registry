import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
@Injectable() 
export class SharedService {

  private messageSource = new BehaviorSubject<string>("");
  message = this.messageSource.asObservable();

  constructor() {

  }
  publishMessage(message:string){
    this.messageSource.next(message);
    console.log(message);
  }
}
