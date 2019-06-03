import {Component} from '@angular/core';
import {UserService} from './service/user-service.service';
import {ActivatedRoute} from '@angular/router';
import {GoogleApiService, GoogleAuthService} from 'ng-gapi';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserProfile} from './data/UserProfile';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'cashmachine-frontend';

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private authService: GoogleAuthService,
              private gapiService: GoogleApiService,
              private http: HttpClient) {
    // First make sure gapi is loaded can be in AppInitilizer
    this.gapiService.onLoad().subscribe();

  }

  // tslint:disable-next-line:use-life-cycle-interface



}
