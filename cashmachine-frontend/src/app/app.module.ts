import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {
  GoogleApiModule,
  GoogleApiService,
  GoogleAuthService,
  NgGapiClientConfig,
  NG_GAPI_CONFIG,
  GoogleApiConfig
} from 'ng-gapi';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthService} from './service/auth-service.service';
import { LoginComponent } from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import {AuthGuard} from './auth/auth.guard';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {JwtInterceptor} from './interceptor/jwt.interceptor';
import {TokenExpirationInterceptor} from './interceptor/token-expiration.interceptor';
import {
  MatButtonModule,
  MatDividerModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatPaginatorModule,
  MatProgressSpinnerModule, MatSortModule, MatTableModule
} from '@angular/material';
import { OrdersComponent } from './orders/orders.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

const gapiClientConfig: NgGapiClientConfig = {
  client_id: '543468181862-jlb9u7fci0600tasvg44vjjj8toqteu0.apps.googleusercontent.com',
  discoveryDocs: ['https://analyticsreporting.googleapis.com/$discovery/rest?version=v4'],
  redirect_uri: 'http://localhost:4200/home',
  scope: [
    'https://www.googleapis.com/auth/userinfo.profile'
  ].join(' ')
};

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard],
    data: { roles: ['cashier', 'admin'] }
  },
  { path: '',
    redirectTo: '/orders',
    pathMatch: 'full'
  },
  { path: '**', component:  PageNotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    PageNotFoundComponent,
    OrdersComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    RouterModule.forRoot([{
      path: 'redirect',
      component: AppComponent
    }]),
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true} // <-- debugging purposes only
    ),
    HttpClientModule,
    GoogleApiModule.forRoot({
      provide: NG_GAPI_CONFIG,
      useValue: gapiClientConfig
    }),
    MatProgressBarModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatSortModule
  ],
  providers: [AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenExpirationInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
