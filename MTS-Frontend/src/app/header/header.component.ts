import { Component, OnInit } from '@angular/core';
import { LogInComponent } from '../log-in/log-in.component';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private loginService:LoginService ) { }

  account:any={};
  isActive:boolean=false;

  ngOnInit(): void {
    this.loginService.activeStatusStream.subscribe((activeStatus:boolean)=>{
      this.isActive=activeStatus;
  });
  this.loginService.activeAccountStream.subscribe((account:any)=>{
      this.account=account;
  });
  }

}
