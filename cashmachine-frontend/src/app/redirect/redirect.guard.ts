import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from '../login/service/auth-service.service';
import {RedirectService} from '../login/service/redirect.service';

@Injectable({
  providedIn: 'root'
})
export class RedirectGuard implements CanActivate {

  constructor(private authService: AuthService, private redirectService: RedirectService, private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    if (this.authService.isUserSignedIn()) {
      this.redirectService.redirectUserToHomePage(this.authService.getUser());
      return false;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

}
