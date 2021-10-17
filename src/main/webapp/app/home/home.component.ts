import { Component, OnInit } from '@angular/core';

import { LoginService } from 'app/login/login.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';
import { HomeService } from './home.service';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  account: Account | null = null;
  notification: any;

  constructor(private accountService: AccountService, private loginService: LoginService, private homeService: HomeService) {}

  ngOnInit(): void {
    this.accountService.identity().subscribe(account => {
      this.account = account;
      this.generateNotification();
    });
  }

  login(): void {
    this.loginService.login();
  }

  generateNotification(): void {
    if (this.accountService.isAuthenticated()) {
      this.homeService.getNotification().subscribe(data => {
        this.notification = data?.response;
      });
    } else {
      this.homeService.getPublicNotification().subscribe(data => {
        this.notification = data?.response;
      });
    }
  }

  isAuthen(): boolean {
    return this.accountService.isAuthenticated();
  }
}
