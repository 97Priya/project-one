import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { TransactionService } from '../transaction.service';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit {

  account:any;

  transactionType:any;

  totalReqTxrns:any;

  showTxrns:boolean=true;

  currentPage:any=0;

  Txrs:any=[];

  constructor(private loginService:LoginService,private txrService:TransactionService) { }

  ngOnInit(): void {
    this.loginService.activeAccountStream.subscribe((account:any)=>{
        this.account=account;
        this.transactionType=this.account.type;
    });
    this.txrService.loadTxr(this.account.account_number).subscribe((txrs:any)=>{
        this.Txrs=txrs;
    });
  }

  handleSelectOnChange(event:any){
    this.totalReqTxrns=event.target.value;
    this.txrService.getTopNTxr(this.account.account_number).subscribe(
      (txrns:any)=>{
          this.Txrs=txrns;
          this.totalReqTxrns=this.totalReqTxrns-5;
      }
    );
  }

  handleDateOnChange(event:any){
    let date=event.target.value;
    this.txrService.getTxrByDate(this.account.account_number,date).subscribe(
      (txrs:any)=>{
        this.Txrs=txrs;
      }
    );
  }

  handleNextClick(event:any){
    event.preventDefault();
      if(this.totalReqTxrns){
          this.currentPage++;
          this.txrService.page=this.currentPage;
          this.txrService.getTopNTxr(this.account.account_number).subscribe(
           (txns:any)=>{
             if(txns){
              this.Txrs=txns
             }
              else{
                console.log("No More Page!!!!!!!!!!!!!!");
                this.showTxrns=false;
              }
            }
          );
      }
  }
}
