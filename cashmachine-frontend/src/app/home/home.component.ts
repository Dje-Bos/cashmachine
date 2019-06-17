import { Component, OnInit } from '@angular/core';
import {UserProfile} from '../data/UserProfile';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthService} from '../service/auth-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private profile: UserProfile = new UserProfile("", "", "", "");

  constructor(private http: HttpClient, private userService: AuthService) { }

  ngOnInit() {
  }
}
