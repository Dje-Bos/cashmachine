import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../../login/service/auth-service.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header-profile',
  templateUrl: './header-profile.component.html',
  styleUrls: ['./header-profile.component.css']
})
export class HeaderProfileComponent implements OnInit {

  private pictureUrl = '';

  constructor(private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    this.pictureUrl = this.authService.getUser().pictureUrl;
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
