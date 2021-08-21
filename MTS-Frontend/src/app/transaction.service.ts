import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { animate } from '@angular/animations';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  page:any=0;

  size:any=5;

  constructor(private httpClient:HttpClient) { }

  loadTxr(acc_number:any){
    return this.httpClient.get(`http://localhost:8082/api/accounts/${acc_number}/txns`)
   }

   getTxrByDate(acc_num:any,date:any){
    return this.httpClient.get(`http://localhost:8082/api/accounts/${acc_num}/txns/date/${date}?page=${this.page}&size=${this.size}`)
   }


getTopNTxr(acc_num:any){
  return this.httpClient.get(`http://localhost:8082/api/${acc_num}/transactions?page=${this.page}&size=${this.size}`); 
}
}