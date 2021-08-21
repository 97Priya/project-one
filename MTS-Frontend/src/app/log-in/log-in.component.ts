import { Component, OnInit } from '@angular/core';
import {AccountService} from "../account.service";
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {

  account:any={};

  loginForm:any=this.fb.group({
    account_holder_name:['', [Validators.required]],
    account_number:['', [Validators.required]]
  });

  isActive:boolean=false;

  constructor(private accountService:AccountService,private loginService:LoginService,private fb: FormBuilder,private router: Router) { }

  ngOnInit(): void {
    
  }

  handleLogIn(event:any){
   let account:any=this.loginForm.value;
   
   if(this.loginForm.valid){
    console.log("log in component");
     this.accountService.getActiveUser(account.account_number).subscribe(
       {
         next:(response:any)=>{
           this.account=response;
           this.isActive=this.loginService.isValidAccount(account,this.account);
           this.router.navigateByUrl("/");
         }
       }
     );
   
    }
  
    
  }



}
