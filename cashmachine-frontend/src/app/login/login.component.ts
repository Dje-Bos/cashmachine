import { Component, OnInit } from '@angular/core';
import {AuthService} from '../service/auth-service.service';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: AuthService, iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIcon(
      'google-icon',
      sanitizer.bypassSecurityTrustResourceUrl('assets/google-icon.svg'));
  }

  public isLoggedIn(): boolean {
    return this.userService.isUserSignedIn();
  }


  public signIn() {
    this.userService.signIn();
  }
  ngOnInit() {
  }

}
