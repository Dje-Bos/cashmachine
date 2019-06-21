import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {AnonymousGuard} from './guard/anonymous.guard';
import {LoginCallbackComponent} from './login-callback/login-callback.component';

const routes: Routes = [
  {path: 'login', component: LoginComponent, canActivate: [AnonymousGuard]},
  {
    path: 'logincallback', component: LoginCallbackComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
