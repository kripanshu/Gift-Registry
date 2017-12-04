import { Injectable } from '@angular/core';
import { Question } from '../models/Question'

@Injectable()
export class DataService {
questions : Question[];
  constructor() {
    this.questions = [
      {
        text:'What is your favorite footballer name?',
        answer: 'My name is Lionel Messi'
    
      },
      {
        text:'What is your favorite color?',
        answer: 'My favorite color is blue'
       
      },
      
    ];
   }

  // this is where questions from database will come from
  getQuestions(){
  return this.questions;
}
}
