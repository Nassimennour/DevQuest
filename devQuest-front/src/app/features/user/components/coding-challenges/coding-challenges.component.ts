import { Component, OnInit } from '@angular/core';
import { CodingChallengesService } from '../../services/coding-challenges.service';
import { UserService } from '../../services/user.service';
import {
  CodingChallenge,
  Solution,
  Technology,
} from '../../../../shared/models/usermodels';
import { Router } from '@angular/router';

@Component({
  selector: 'app-coding-challenges',
  templateUrl: './coding-challenges.component.html',
  styleUrls: ['./coding-challenges.component.css'],
})
export class CodingChallengesComponent implements OnInit {
  challenges: CodingChallenge[] = [];
  filteredChallenges: CodingChallenge[] = [];
  paginatedChallenges: CodingChallenge[] = [];
  technologies: Technology[] = [];
  userSolutions: Solution[] = [];
  searchTerm: string = '';
  selectedTechnology: string = '';
  sortOrder: string = 'title';
  currentPage: number = 1;
  itemsPerPage: number = 6;
  totalPages: number = 1;

  constructor(
    private challengesService: CodingChallengesService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchChallenges();
    this.fetchTechnologies();
    this.fetchUserSolutions();
  }

  fetchChallenges(): void {
    this.challengesService.getAllChallenges().subscribe(
      (challenges) => {
        this.challenges = challenges;
        this.filteredChallenges = challenges;
        this.totalPages = Math.ceil(
          this.filteredChallenges.length / this.itemsPerPage
        );
        this.paginateChallenges();
      },
      (error) => {
        console.error('Error fetching challenges:', error);
      }
    );
  }

  fetchTechnologies(): void {
    this.userService.getAllTechnologies().subscribe(
      (technologies) => {
        this.technologies = technologies;
      },
      (error) => {
        console.error('Error fetching technologies:', error);
      }
    );
  }

  fetchUserSolutions(): void {
    this.challengesService.getMySolutions().subscribe(
      (solutions) => {
        this.userSolutions = solutions;
      },
      (error) => {
        console.error('Error fetching user solutions:', error);
      }
    );
  }

  filterChallenges(): void {
    this.filteredChallenges = this.challenges;

    if (this.searchTerm) {
      this.filteredChallenges = this.filteredChallenges.filter((challenge) =>
        challenge.title?.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }

    if (this.selectedTechnology) {
      this.filteredChallenges = this.filteredChallenges.filter(
        (challenge) => challenge.technology?.name === this.selectedTechnology
      );
    }

    this.totalPages = Math.ceil(
      this.filteredChallenges.length / this.itemsPerPage
    );
    this.currentPage = 1;
    this.paginateChallenges();
  }

  sortChallenges(): void {
    this.filteredChallenges.sort((a, b) => {
      if (this.sortOrder === 'title') {
        return a.title!.localeCompare(b.title!);
      } else if (this.sortOrder === 'difficulty') {
        return a.difficulty!.localeCompare(b.difficulty!);
      } else if (this.sortOrder === 'duration') {
        return a.duration! - b.duration!;
      }
      return 0;
    });
    this.paginateChallenges();
  }

  paginateChallenges(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedChallenges = this.filteredChallenges.slice(
      startIndex,
      endIndex
    );
  }

  previousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.paginateChallenges();
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.paginateChallenges();
    }
  }

  hasUserTakenChallenge(challengeId: number | undefined): boolean {
    return this.userSolutions.some(
      (solution) => solution.codingChallenge?.id === challengeId
    );
  }

  viewChallengeDetails(challengeId: number | undefined): void {
    this.router.navigate(['/user/coding-challenge', challengeId]);
  }

  takeChallenge(challengeId: number | undefined): void {
    this.router.navigate(['/user/take-challenge', challengeId]);
  }

  viewUserSolution(challengeId: number | undefined): void {
    let solutionId = this.userSolutions.find(
      (solution) => solution.codingChallenge?.id === challengeId
    )?.id;
    if (solutionId) {
      this.router.navigate([
        '/user/challenge',
        challengeId,
        'solution',
        solutionId,
      ]);
    }
  }
}
