import { Component, OnInit } from '@angular/core';
import {AuthService} from '../service/auth-service.service';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry} from '@angular/material';
import {FormControl, FormGroup} from '@angular/forms';
import {flatMap} from 'rxjs/operators';
import {RedirectService} from '../service/redirect.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });
  constructor(private router: Router, private redirectService: RedirectService, private authService: AuthService, iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIcon(
      'google-icon',
      sanitizer.bypassSecurityTrustResourceUrl('assets/google-icon.svg'));
  }

  public isLoggedIn(): boolean {
    return this.authService.isUserSignedIn();
  }

  ngOnInit() {
  }

  regularLogin() {
    this.authService.signIn(this.loginForm.value.email, this.loginForm.value.password).pipe(
      flatMap(((value, index) => value))
    ).subscribe((result) => {
      if (result) {
        this.redirectService.redirectUserToHomePage(this.authService.getUser(), this.authService.redirectUri);
      } else {
        this.logoutAndNavigateToLogin();
      }
    });
  }
  private logoutAndNavigateToLogin() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
