import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {UserProfile} from '../../data/UserProfile';
import {AuthService} from '../service/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class UserResolveGuard implements Resolve<UserProfile> {
  constructor(private authService: AuthService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<UserProfile> | Promise<UserProfile> | UserProfile {
    return this.authService.getCurrUserInfo();
  }

}
