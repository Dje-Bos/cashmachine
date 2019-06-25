import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {UserProfile} from '../../data/UserProfile';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private static TOKEN_STORAGE_KEY = 'accessToken';
  private static USER_STORAGE_KEY = 'user';
  public static HOST = 'http://localhost:4200';
  private _authCallBackUri: string = AuthService.HOST + '/logincallback';
  private _redirectUri: string = null;

  constructor(private router: Router, private http: HttpClient) {
  }

  public getToken(): string {
    return localStorage.getItem(AuthService.TOKEN_STORAGE_KEY);
  }

  public signInWithOAuth(providerId: string) {
    return window.open(`http://localhost:8888/cashmachine/oauth2/authorize/${providerId}?redirect_uri=${this.authCallBackUri}`, '_self');
  }


  public signIn(email: string, password: string): Observable<Observable<boolean>> {
    return this.http.post('http://localhost:8888/cashmachine/auth/login', {email, password}).pipe(map(
      resp => {
        return this.loginWithToken(resp['accessToken']);
      })
    );
  }

  public loginWithToken(token): Observable<boolean> {
    if (token) {
      console.log('Setting token');
      localStorage.setItem(AuthService.TOKEN_STORAGE_KEY, token);
      return this.getCurrUserInfo().pipe(map((user: UserProfile) => {
        localStorage.setItem(AuthService.USER_STORAGE_KEY, JSON.stringify(user));
        return true;
      }));
    }

    // catchError(err => {
    //   console.log(err);
    //   of(false);
    // })
    // );
    return of(false);
  }

  public getCurrUserInfo(): Observable<UserProfile> {
    return this.http.get<UserProfile>('http://localhost:8888/cashmachine/api/user/me');
  }

  public logout() {
    console.log('Logging out user ' + this.getUser().email);
    localStorage.removeItem(AuthService.TOKEN_STORAGE_KEY);
    localStorage.removeItem(AuthService.USER_STORAGE_KEY);
  }

  public isUserSignedIn(): boolean {
    return this.getToken() != null && this.getUser() !== undefined;
  }

 public  hasRole(role: string): boolean {
    let user: UserProfile = JSON.parse(localStorage.getItem(AuthService.USER_STORAGE_KEY));
    return user.roles.includes(role);
  }

  public hasRoles(roles: string[]): boolean {
    let results: boolean[] = roles.map(value => {
        return this.hasRole(value);
      }
    );
    if (results.every(value => value === true)) {
      return true;
    } else {
      return false;
    }
  }
  public hasAnyRoles(roles: string[]): boolean {
    let results: boolean[] = roles.map(value => {
        return this.hasRole(value);
      }
    );
    if (results.find(value => value === true)) {
      return true;
    } else {
      return false;
    }
  }

  get authCallBackUri(): string {
    return this._authCallBackUri;
  }

  set authCallBackUri(value: string) {
    this._authCallBackUri = value;
  }

  getUser(): UserProfile {
    const user = localStorage.getItem(AuthService.USER_STORAGE_KEY);
    if (user) {
      return JSON.parse(user);
    } else {
      throw new Error('User is null. Make sure that you get user in authenticated context');
    }
  }

  get redirectUri(): string {
    return this._redirectUri;
  }

  set redirectUri(value: string) {
    if (value.length > 0) {
      this._redirectUri = value;
    }
  }
}
