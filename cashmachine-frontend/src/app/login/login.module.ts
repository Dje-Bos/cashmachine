import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {LoginRoutingModule} from './login-routing.module';
import {LoginComponent} from './login/login.component';
import {AuthService} from './service/auth-service.service';
import {
  MatButtonModule,
  MatDividerModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatProgressSpinnerModule
} from '@angular/material';
import {LoginCallbackComponent} from './login-callback/login-callback.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [LoginComponent, LoginCallbackComponent],
  imports: [
    CommonModule,
    LoginRoutingModule,
    MatFormFieldModule,
    MatDividerModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatProgressSpinnerModule,
    ReactiveFormsModule
  ],
  providers: [AuthService]
})
export class LoginModule {
}
