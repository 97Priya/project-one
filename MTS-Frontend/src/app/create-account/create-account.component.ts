import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  createAccForm:any=this.fb.group({
    account_holder_name:['', [Validators.required]],
    balance:['', [Validators.required]]
  });
  constructor(private accountService:AccountService,private fb: FormBuilder) { }

  accountCreationStatus:boolean=false;

  ngOnInit(): void {
  }
  handleAccCreate(event:any){
    let account=this.createAccForm.value;
    this.accountService.createANewAcc(account).subscribe(()=>{
        this.accountCreationStatus=true;
        this.createAccForm.reset();
    });
  }
}
