import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../service/auth/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isUserAdmin : boolean;
  isUserManager: boolean;
  isUserTeamLead: boolean;

  constructor(private router: Router, private authenticationService : AuthenticationService) { }

  ngOnInit() {
    //this.authenticationService.clearUserSessionData();
    this.isUserAdmin = this.authenticationService.isUserAdmin();
    this.isUserManager = this.authenticationService.isUserManager();
    this.isUserTeamLead = this.authenticationService.isUserLead();
  }

  list(items : String): void {
    this.router.navigate([items]);
  }
}
