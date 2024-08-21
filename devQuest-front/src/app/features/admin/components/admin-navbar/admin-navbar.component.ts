import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from '../../../../shared/services/auth.service';
import { Router } from '@angular/router';
import { UserProfile } from '../../../../shared/models/usermodels';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrl: './admin-navbar.component.css',
})
export class AdminNavbarComponent implements OnInit {
  // adminProfile
  adminProfile: UserProfile | undefined;

  @Output() toggleSideNav = new EventEmitter<void>();

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.getUserProfile().subscribe(
      (profile) => {
        this.adminProfile = profile;
      },
      (error) => {
        console.error('Error getting user profile from component ', error);
      }
    );
  }

  onToggleSideNav(): void {
    this.toggleSideNav.emit();
  }
}
