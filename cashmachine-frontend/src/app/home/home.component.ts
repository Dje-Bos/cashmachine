import {Component, OnInit} from '@angular/core';
import {UserProfile} from '../data/UserProfile';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../login/service/auth-service.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: UserProfile;
  constructor(private http: HttpClient, private userService: AuthService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.user = this.route.snapshot.data['user'];
  }
}
