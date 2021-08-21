import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AccountService } from '../account.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  paymentForm:any=this.fb.group({
    toAccount:['', [Validators.required]],
    amount:['',[Validators.required]]
  });

  account:any={ };

  paymentSucessStatus:boolean=false;

  paymentErrorStatus:boolean=false;

  constructor(private fb: FormBuilder,private accountService:AccountService,private loginService:LoginService) { }

  ngOnInit(): void {
    this.loginService.activeAccountStream.subscribe((account:any)=>{
      this.account=account;
    });
  }

  handleTrx(event:any){
    if(this.paymentForm.valid){
      let txr=this.paymentForm.value;
      txr.fromAccount=this.account.account_number;
      this.accountService.doTxr(txr).subscribe(
        {
          next:(response:any)=>{
            console.log(response);
            if(response.message=="SUCESS"){
              this.paymentSucessStatus=true; 
            }
            else{
              this.paymentErrorStatus=true;
            }
            this.paymentForm.reset(); 
          }
        }
      )
    }
  }
  
}
