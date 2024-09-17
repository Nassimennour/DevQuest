import {
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import { Toast } from 'bootstrap';
import {
  CodingChallenge,
  Dashboard,
  Ranking,
  UserProfile,
} from '../../../../shared/models/usermodels';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { CodingChallengesService } from '../../services/coding-challenges.service';
import { Solution } from '../../../admin/models/admin-models';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css',
})
export class UserDashboardComponent implements AfterViewInit, OnInit {
  userProfile: UserProfile | null = null;
  userRanking: Ranking | null = null;
  userDashboard: Dashboard | null = null;
  userSolutions: Solution[] = [];

  @ViewChild('toast') toast!: ElementRef;

  constructor(
    private userService: UserService,
    private router: Router,
    private CodingChallengesService: CodingChallengesService
  ) {}

  ngOnInit(): void {
    this.fetchUserProfile();
    this.fetchUserRanking();
    this.fetchUserDashboard();
    this.fetchUserSolutions();
  }

  ngAfterViewInit(): void {
    const toast = new Toast(this.toast.nativeElement);
    toast.show();
  }

  fetchUserProfile(): void {
    this.userService.getMyProfile().subscribe(
      (profile) => {
        this.userProfile = profile;
      },
      (error) => {
        console.error('Error fetching user profile:', error);
      }
    );
  }

  fetchUserRanking(): void {
    this.userService.getMyRanking().subscribe(
      (ranking) => {
        this.userRanking = ranking;
      },
      (error) => {
        console.error('Error fetching user ranking:', error);
      }
    );
  }

  fetchUserDashboard(): void {
    this.userService.getMyDashboard().subscribe(
      (dashboard) => {
        console.log('User dashboard:', dashboard);
        this.userDashboard = dashboard;
      },
      (error) => {
        console.error('Error fetching user dashboard:', error);
      }
    );
  }

  viewQuizDetails(quizId: number | undefined): void {
    this.router.navigate(['/user/quizz', quizId]);
  }

  takeQuiz(quizId: number | undefined): void {
    this.router.navigate(['/user/take-quiz', quizId]);
  }

  viewChallengeDetails(challengeId: number | undefined): void {
    this.router.navigate(['/user/coding-challenge', challengeId]);
  }

  takeChallenge(challengeId: number | undefined): void {
    this.router.navigate(['/user/take-challenge', challengeId]);
  }
  fetchUserSolutions(): void {
    this.CodingChallengesService.getMySolutions().subscribe(
      (solutions) => {
        this.userSolutions = solutions;
      },
      (error) => {
        console.error('Error fetching user solutions:', error);
      }
    );
  }
}
