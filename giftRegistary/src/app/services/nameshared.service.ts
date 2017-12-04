import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
@Injectable()
export class NamesharedService {
  private namemessageSource = new BehaviorSubject<string>("");
  namemessage = this.namemessageSource.asObservable();
  
  constructor() { }
  
  publishMessage(message:string){
    this.namemessageSource.next(message);
  }
}
