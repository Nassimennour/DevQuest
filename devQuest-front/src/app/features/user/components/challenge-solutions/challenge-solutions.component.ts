import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CodingChallengesService } from '../../services/coding-challenges.service';
import { UserService } from '../../services/user.service';
import { Observable } from 'rxjs';
import { Solution, UserProfile } from '../../../../shared/models/usermodels';

@Component({
  selector: 'app-challenge-solutions',
  templateUrl: './challenge-solutions.component.html',
  styleUrls: ['./challenge-solutions.component.css'],
})
export class ChallengeSolutionsComponent implements OnInit {
  solutions: Solution[] = [];
  userSolutions: Solution[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 5;
  totalPages: number = 1;
  challengeId: number | null = null;
  currentUser: UserProfile | null = null;
  selectedSolution: Solution | null = null;

  constructor(
    private codingChallengesService: CodingChallengesService,
    private userService: UserService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.challengeId = +this.route.snapshot.paramMap.get('challengeId')!;
    if (this.challengeId) {
      this.loadSolutions(this.challengeId);
    }
    this.loadCurrentUser();
  }

  loadSolutions(challengeId: number): void {
    this.codingChallengesService
      .getAllSolutionsByChallengeId(challengeId)
      .subscribe((solutions) => {
        this.solutions = solutions.sort(
          (a, b) =>
            new Date(b.submissionDate!).getTime() -
            new Date(a.submissionDate!).getTime()
        );
        this.totalPages = Math.ceil(this.solutions.length / this.itemsPerPage);
        this.userSolutions = this.solutions.filter(
          (solution) => solution.user.id === this.currentUser?.id
        );
      });
  }

  loadCurrentUser(): void {
    this.userService.getMyProfile().subscribe((user) => {
      this.currentUser = user;
    });
  }

  get paginatedSolutions(): Solution[] {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return this.solutions.slice(startIndex, startIndex + this.itemsPerPage);
  }

  previousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
    }
  }

  openSolutionModal(solution: Solution): void {
    this.selectedSolution = solution;
  }

  closeSolutionModal(): void {
    this.selectedSolution = null;
  }
}
