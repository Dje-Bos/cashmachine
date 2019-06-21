import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {UserProfile} from '../../data/UserProfile';
import {AuthService} from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class RedirectService {

  private homePages: Map<string, string> = new Map<string, string>([
      ['CASHIER', '/orders'],
      ['SENIOR_CASHIER', '/orders'],
      ['MERCHANDISE', '/products'],
      ['ADMIN', '/orders']
    ]
  );

  constructor(private authService: AuthService, private router: Router) {
  }

  public redirectUserToHomePage(user: UserProfile, desiredRoute?: string) {
    if (desiredRoute) {
      if (this.validatePathForUser(desiredRoute, user)) {
        this.router.navigate([desiredRoute]);
      } else {
        this.findHomePageForUser(user);
      }
    } else {
      this.findHomePageForUser(user);
    }
  }

  private findHomePageForUser(user: UserProfile) {
    const homePage: string = this.homePages.get(user.roles[0]);
    if (homePage) {
      this.router.navigate([homePage]);
    } else {
      console.log('Error determine user home page for user = ' + user.email);
      this.router.navigate(['/error']);
    }
  }

  private validatePathForUser(path: string, user: UserProfile): boolean {
    let result = false;
    const allowedPaths: string[] = [];
    this.homePages.forEach(((value, key) => {
      if (user.roles.includes(key)) {
        allowedPaths.push(value);
      }
    }));
    allowedPaths.forEach(value => {
      if (path.startsWith(value)) {
        result = true;
      }
    });
    return result;
  }
}
