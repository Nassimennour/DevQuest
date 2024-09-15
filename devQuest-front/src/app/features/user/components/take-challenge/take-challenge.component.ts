import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CodingChallengesService } from '../../services/coding-challenges.service';
import {
  CodingChallenge,
  SolutionDTO,
  UpdateSolutionDTO,
} from '../../../../shared/models/usermodels';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-take-challenge',
  templateUrl: './take-challenge.component.html',
  styleUrls: ['./take-challenge.component.css'],
})
export class TakeChallengeComponent implements OnInit {
  challenge: CodingChallenge | null = null;
  solution: SolutionDTO & { id?: number } = {
    code: '',
    codingChallengeId: 0,
    userId: 0,
    isCorrect: false,
    submissionDate: '',
  };
  isSubmitted: boolean = false;
  submissionMessage: string = '';

  constructor(
    private challengesService: CodingChallengesService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const challengeId = this.route.snapshot.paramMap.get('id');
    if (challengeId) {
      this.fetchChallengeDetails(+challengeId);
      this.userService.getMyProfile().subscribe(
        (profile) => {
          this.solution.userId = profile.id!;
        },
        (error) => {
          console.error('Error fetching user profile:', error);
        }
      );
    }
  }

  fetchChallengeDetails(id: number): void {
    this.challengesService.getCodingChallengeById(id).subscribe(
      (challenge) => {
        this.challenge = challenge;
        this.solution.codingChallengeId = challenge.id!;
      },
      (error) => {
        console.error('Error fetching challenge details:', error);
      }
    );
  }

  submitSolution(): void {
    if (this.isSubmitted) {
      const updateSolution: UpdateSolutionDTO = {
        id: this.solution.id,
        code: this.solution.code,
        isCorrect: this.solution.isCorrect,
        submissionDate: new Date().toISOString(),
      };
      this.challengesService.updateSolution(updateSolution).subscribe(
        (response) => {
          this.submissionMessage = 'Solution updated successfully!';
          this.isSubmitted = true;
        },
        (error) => {
          console.error('Error updating solution:', error);
        }
      );
    } else {
      this.solution.submissionDate = new Date().toISOString();
      this.challengesService.saveSolution(this.solution).subscribe(
        (response) => {
          this.solution.id = response.id;
          this.submissionMessage = 'Solution submitted successfully!';
          this.isSubmitted = true;
        },
        (error) => {
          console.error('Error submitting solution:', error);
        }
      );
    }
  }

  editSolution(): void {
    this.isSubmitted = false;
    this.submissionMessage = '';
  }

  goBack(): void {
    this.router.navigate(['/user/coding-challenge', this.challenge?.id]);
  }
}
