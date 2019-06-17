import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from '../service/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
    Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log('AuthGuard#canActivate called');
    if (this.authService.isUserSignedIn()) {
      return true;
    } else {
      if (route.queryParams.token) {
        this.authService.loginWithToken(route.queryParams.token);
        return true;
      } else {
        this.authService.defaultRedirecturi = state.url;
        this.router.navigate(['/login']);
        return false;
      }
    }
  }

}
