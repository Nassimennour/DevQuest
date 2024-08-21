import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import { Toast } from 'bootstrap';
import { UserProfile } from '../../../../shared/models/usermodels';
import { AuthService } from '../../../../shared/services/auth.service';
import { Observable, tap } from 'rxjs';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css',
})
export class UserDashboardComponent implements AfterViewInit, OnInit {
  userProfile$: Observable<UserProfile> | undefined;

  @ViewChild('toast') toast!: ElementRef;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.userProfile$ = this.authService.getUserProfile();
    this.userProfile$.subscribe(
      (profile) => {
        console.log('User profile from component: ', profile);
      },
      (error) => {
        console.error('Error getting user profile from component ', error);
      }
    );
  }

  ngAfterViewInit(): void {
    const toast = new Toast(this.toast.nativeElement);
    toast.show();
  }
}
