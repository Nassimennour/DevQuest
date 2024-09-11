import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersService } from '../../../services/users.service';
import { Ranking, User } from '../../../models/admin-models';
import { RankingService } from '../../../services/ranking.service';
import { Tooltip } from 'bootstrap';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css',
})
export class UserDetailsComponent implements OnInit {
  userId: string | null;
  userData!: User;
  userRanking!: Ranking;
  constructor(
    private activatedRoute: ActivatedRoute,
    private usersService: UsersService,
    private rankingService: RankingService
  ) {
    this.userId = this.activatedRoute.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    if (this.userId) {
      // get user data
      this.usersService.getUserById(Number(this.userId)).subscribe(
        (data) => {
          console.log(data, 'userData#');
          this.userData = data;
          // Get user ranking
          this.getUserRanking();
        },
        (error) => {
          console.error('Error fetching user data: ', error);
        }
      );
    }

    const toolTipTriggerList = [].slice.call(
      document.querySelectorAll('[data-bs-toggle="tooltip"]')
    );
    toolTipTriggerList.forEach(function (tooltipTriggerEl) {
      new Tooltip(tooltipTriggerEl);
    });
  }

  getUserRanking(): void {
    this.rankingService.getRankingByUserId(this.userId as string).subscribe(
      (ranking) => {
        this.userRanking = ranking;
        console.log(this.userRanking, 'userRanking#');
      },
      (error) => {
        console.error('Error fetching user ranking', error);
      }
    );
  }
}
