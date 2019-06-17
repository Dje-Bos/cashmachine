import {Injectable} from '@angular/core';
import {GoogleAuthService} from 'ng-gapi';
import GoogleUser = gapi.auth2.GoogleUser;
import GoogleAuth = gapi.auth2.GoogleAuth;
import * as _ from 'lodash';
import {Router} from '@angular/router';
import {UserProfile} from '../data/UserProfile';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public static SESSION_STORAGE_KEY = 'accessToken';
  private _user: UserProfile;
  private _defaultRedirecturi: string = '';

  constructor(private googleAuth: GoogleAuthService, private router: Router, private http: HttpClient) {
  }

  public getToken(): string {
    const token: string = localStorage.getItem(AuthService.SESSION_STORAGE_KEY);
    if (!token) {
      throw new Error('no token set , authentication required');
    }
    return localStorage.getItem(AuthService.SESSION_STORAGE_KEY);
  }

  public signIn(providerId: string, redirectUri: string) {
    if (!redirectUri) {
      redirectUri = this.defaultRedirecturi;
    }
    return this.http.get(`http://localhost:8888/cashmachine/oauth2/authorize/${providerId}?redirect_uri=${redirectUri}`).subscribe(
      () => this.loginSuccess(),
      (error) => this.signInErrorHandler(error));
  }

  public loginWithToken(token): UserProfile {
    if (token) {
      localStorage.setItem(AuthService.SESSION_STORAGE_KEY, token);
      this.loadInfo();
    }
    return this._user;
  }

  public  loadInfo() {
      this.getCurrUserInfo().subscribe((data: UserProfile) => this._user = data);
  }

  public getCurrUserInfo() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Bearer ' + this.getToken()
      })
    };
    return this.http.get<UserProfile>('https://www.googleapis.com/oauth2/v1/userinfo?alt=json', httpOptions);
  }

  public logout() {
    localStorage.removeItem(AuthService.SESSION_STORAGE_KEY);
    this._user = null;
  }

  public isUserSignedIn(): boolean {
    return !_.isEmpty(sessionStorage.getItem(AuthService.SESSION_STORAGE_KEY));
  }

  private signInErrorHandler(err) {
    console.warn(err);
  }

  private loginSuccess() {
    console.log('success login');
  }


  get user(): UserProfile {
    return this._user;
  }


  get defaultRedirecturi(): string {
    return this._defaultRedirecturi;
  }

  set defaultRedirecturi(value: string) {
    this._defaultRedirecturi = value;
  }
}
