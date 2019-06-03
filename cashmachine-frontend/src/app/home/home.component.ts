import { Component, OnInit } from '@angular/core';
import {UserProfile} from '../data/UserProfile';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserService} from '../service/user-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private profile: UserProfile = new UserProfile("", "", "");

  constructor(private http: HttpClient, private userService: UserService) { }

  ngOnInit() {
    this.loadInfo();
  }

  public  loadInfo() {
    this.getCurrUserInfo().subscribe((data: UserProfile) => this.profile = {... data});
  }

  public getCurrUserInfo() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Bearer ' + this.userService.getToken()
      })
    };
    return this.http.get<UserProfile>('https://www.googleapis.com/oauth2/v1/userinfo?alt=json', httpOptions);
  }
}
