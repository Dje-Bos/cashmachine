import {AuthService} from '../login/service/auth-service.service';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {tap} from 'rxjs/operators';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenExpirationInterceptor implements HttpInterceptor {
  constructor(public auth: AuthService, public router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(request)
      .pipe(
        tap(event => {
          if (event instanceof HttpResponse) {
            // skip if everything is ok
          }
        }, err => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === 401) {
              this.auth.logout();
              this.router.navigate(['/login']);
            }
          }
        }));
  }
}
