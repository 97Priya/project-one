import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  account:any={};
  constructor(private httpClient:HttpClient) { }

 

  getActiveUser(acc_num:any){
    console.log("getting account req");
  return this.httpClient.get(`http://localhost:8082/api/accounts/${acc_num}`);
  }

   createANewAcc(acc:any){
    return this.httpClient.post(`http://localhost:8082/api/accounts/new`,acc);
   }
   
   doTxr(body:any){
    return this.httpClient.post(`http://localhost:8082/api/txr`,body);
   }
}
