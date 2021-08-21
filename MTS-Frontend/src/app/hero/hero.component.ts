import { Component, OnInit } from '@angular/core';
import {AccountService} from "../account.service";
import { LoginService } from '../login.service';


@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.css']
})
export class HeroComponent implements OnInit {


  isActive:boolean=false;

  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
    this.loginService.activeStatusStream.subscribe((status:boolean)=>{
        this.isActive=!status;
    });
  }


}
