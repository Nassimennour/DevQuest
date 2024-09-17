import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Ranking, UserProfile } from '../../../../shared/models/usermodels';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css'],
})
export class RankingComponent implements OnInit {
  rankings: Ranking[] = [];
  loggedInUser: UserProfile | null = null;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadRankings();
    this.loadLoggedInUser();
  }

  loadRankings(): void {
    this.userService.getAllRankings().subscribe(
      (rankings) => {
        this.rankings = rankings.sort((a, b) => a.position! - b.position!);
      },
      (error) => {
        console.error('Error loading rankings:', error);
      }
    );
  }

  loadLoggedInUser(): void {
    this.userService.getMyProfile().subscribe(
      (user) => {
        this.loggedInUser = user;
      },
      (error) => {
        console.error('Error loading logged-in user:', error);
      }
    );
  }

  isLoggedInUser(userId: number): boolean {
    return this.loggedInUser ? this.loggedInUser.id === userId : false;
  }

  getLevelClass(level: number | undefined): string {
    switch (level) {
      case 1:
        return 'level-one';
      case 2:
        return 'level-two';
      case 3:
        return 'level-three';
      default:
        return '';
    }
  }
}
