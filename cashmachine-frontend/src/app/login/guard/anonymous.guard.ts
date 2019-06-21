import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {AuthService} from '../service/auth-service.service';
import {RedirectService} from '../service/redirect.service';

@Injectable({
  providedIn: 'root'
})
export class AnonymousGuard implements CanActivate {
  constructor(private homePageService: RedirectService, private authService: AuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    console.log('anonymous guard user signed in =' + this.authService.isUserSignedIn());
    if (this.authService.isUserSignedIn()) {
      console.log('anonymous user detected, current user = ' + this.authService.getUser());
      this.homePageService.redirectUserToHomePage(this.authService.getUser());
      return false;
    } else {
      return true;
    }
  }
}
