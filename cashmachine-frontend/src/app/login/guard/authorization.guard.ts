import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable, of} from 'rxjs';
import {AuthService} from '../service/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard implements CanActivate {

  constructor(private authService: AuthService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> {
    // return this.authService.getCurrUserInfo().pipe(
    //       map((json) => {
    //       console.log('actual roles = ' + json.roles);
    //       console.log('actual roles size = ' + json.roles.length);
    //       const expectedRoles: string[] = next.data.roles;
    //       console.log('expected roles = ' + expectedRoles);
    //       console.log('expected roles size = ' + expectedRoles.length);
    //       if (expectedRoles.length > 0) {
    //         return expectedRoles.some((r) => {
    //           console.log('matching ' + r);
    //           const result: boolean = json.roles.indexOf(r) >= 0;
    //           console.log('result ' + result);
    //           return result;
    //         });
    //       } else {
    //         return true;
    //       }
    //     }));
    const userRoles: string[] = this.authService.getUser().roles;
    console.log('actual roles = ' + userRoles);
    console.log('actual roles size = ' + userRoles.length);
    const expectedRoles: string[] = next.data.roles;
    console.log('expected roles = ' + expectedRoles);
    console.log('expected roles size = ' + expectedRoles.length);
    if (expectedRoles.length > 0) {
      return of(expectedRoles.some((r) => {
        console.log('matching ' + r);
        const result: boolean = userRoles.indexOf(r) >= 0;
        console.log('result ' + result);
        return result;
      }));
    } else {
      return of(true);
    }
  }
}
