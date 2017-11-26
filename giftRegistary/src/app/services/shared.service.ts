import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
@Injectable()
export class SharedService {

  private messageSource = new BehaviorSubject<String>("");
  message = this.messageSource.asObservable();

  constructor() {

  }
  publishMessage(message:String){
    this.messageSource.next(message);
  }
}
