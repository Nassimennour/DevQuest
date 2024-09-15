// challenge.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CodingChallengesService } from '../../services/coding-challenges.service';
import { CodingChallenge } from '../../../../shared/models/usermodels';

@Component({
  selector: 'app-challenge',
  templateUrl: './challenge.component.html',
  styleUrls: ['./challenge.component.css'],
})
export class ChallengeComponent implements OnInit {
  challenge: CodingChallenge | null = null;

  constructor(
    private challengesService: CodingChallengesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const challengeId = this.route.snapshot.paramMap.get('id');
    if (challengeId) {
      this.fetchChallengeDetails(+challengeId);
    }
  }

  fetchChallengeDetails(id: number): void {
    this.challengesService.getCodingChallengeById(id).subscribe(
      (challenge) => {
        this.challenge = challenge;
      },
      (error) => {
        console.error('Error fetching challenge details:', error);
      }
    );
  }

  startChallenge(): void {
    this.router.navigate(['/user/take-challenge', this.challenge?.id]);
  }
}
