import {Injectable} from '@angular/core';
import {GoogleAuthService} from 'ng-gapi';
import GoogleUser = gapi.auth2.GoogleUser;
import GoogleAuth = gapi.auth2.GoogleAuth;
import * as _ from 'lodash';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
@Injectable()
export class UserService {
  public static SESSION_STORAGE_KEY = 'accessToken';
  private user: GoogleUser;
  private _redirectUrl: string;

  constructor(private googleAuth: GoogleAuthService, private router: Router) {
  }

  public getToken(): string {
    const token: string = sessionStorage.getItem(UserService.SESSION_STORAGE_KEY);
    if (!token) {
      throw new Error('no token set , authentication required');
    }
    return sessionStorage.getItem(UserService.SESSION_STORAGE_KEY);
  }

  public signIn() {
    return this.googleAuth.getAuth()
      .subscribe((auth) => {
        auth.signIn().then(res => this.signInSuccessHandler(res));
      });
  }

  private signInSuccessHandler(res: GoogleUser) {
    this.user = res;
    sessionStorage.setItem(
      UserService.SESSION_STORAGE_KEY, res.getAuthResponse().access_token
    );
    if (this.redirectUrl) {
      this.router.navigate([this.redirectUrl]);
    } else {
      this.router.navigate(['/home']);
    }
  }

  public setUser(user: GoogleUser): void {
    this.user = user;
  }

  public getCurrentUser(): GoogleUser {
    return this.user;
  }

  public isUserSignedIn(): boolean {
    return !_.isEmpty(sessionStorage.getItem(UserService.SESSION_STORAGE_KEY));
  }

  private signInErrorHandler(err) {
    console.warn(err);
  }

  set redirectUrl(value: string) {
    this._redirectUrl = value;
  }
}
