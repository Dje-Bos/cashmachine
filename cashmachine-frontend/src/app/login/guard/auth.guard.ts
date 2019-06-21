import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot, UrlSegment, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from '../service/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {

  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
    Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    console.log('AuthGuard#canActivate called');
    if (this.authService.isUserSignedIn()) {
      console.log('current user = ' + this.authService.getUser().email);
      return true;
    } else {
      console.log('redirect to login');
      this.authService.redirectUri = state.url;
      this.router.navigate(['/login']);
      return true;
    }
  }

  canLoad(route: Route, segments: UrlSegment[]): boolean {
    return this.authService.isUserSignedIn();
  }

}
