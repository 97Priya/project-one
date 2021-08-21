import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AccountService } from './account.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  

  activeStatusStream = new BehaviorSubject<boolean>(false)

  activeAccountStream=new BehaviorSubject<{}>(0);

  constructor(private accountService:AccountService) { }

  isValidAccount(inputAcc:any,dbAcc:any):boolean{
    if(inputAcc.account_number==dbAcc.account_number){
      this.activeStatusStream.next(true);
      this.activeAccountStream.next(dbAcc);
      return true;   
    }
      else
      return false;
  }
}
