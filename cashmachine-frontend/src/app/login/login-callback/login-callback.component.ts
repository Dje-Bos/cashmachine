import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../service/auth-service.service';
import {RedirectService} from '../service/redirect.service';

@Component({
  selector: 'app-login-callback',
  templateUrl: './login-callback.component.html',
  styleUrls: ['./login-callback.component.css']
})
export class LoginCallbackComponent implements OnInit {

  constructor(private redirectService: RedirectService, private authService: AuthService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    if (this.route.snapshot.queryParamMap.get('token')) {
      this.authService.loginWithToken(this.route.snapshot.queryParamMap.get('token')).subscribe((result: boolean) => {
        if (result) {
          this.redirectService.redirectUserToHomePage(this.authService.getUser(), this.authService.redirectUri);
        } else {
          this.logoutAndNavigateToLogin();
        }
      });
    } else {
      this.logoutAndNavigateToLogin();
    }
  }

  private logoutAndNavigateToLogin() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
