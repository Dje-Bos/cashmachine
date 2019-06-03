import { Component, OnInit } from '@angular/core';
import {UserService} from '../service/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService) { }

  public isLoggedIn(): boolean {
    return this.userService.isUserSignedIn();
  }


  public signIn() {
    this.userService.signIn();
  }
  ngOnInit() {
  }

}
