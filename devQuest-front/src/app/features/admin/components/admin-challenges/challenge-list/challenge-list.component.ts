import { Component, OnInit } from '@angular/core';
import { CodingChallenge } from '../../../models/admin-models';
import { ChallengeService } from '../../../services/challenge.service';

@Component({
  selector: 'app-challenge-list',
  templateUrl: './challenge-list.component.html',
  styleUrls: ['./challenge-list.component.css'],
})
export class ChallengeListComponent implements OnInit {
  challenges: CodingChallenge[] = [];
  paginatedChallenges: CodingChallenge[] = [];
  filterText = '';
  filterDifficulty = '';
  currentPage = 1;
  itemsPerPage = 6;
  totalPages = 0;
  totalPagesArray: number[] = [];

  constructor(private challengeService: ChallengeService) {}

  ngOnInit(): void {
    this.fetchChallenges();
  }

  fetchChallenges(): void {
    this.challengeService.getAllChallenges().subscribe((data) => {
      this.challenges = data;
      console.log('Coding Challenges: ', this.challenges);
      this.applyFilters();
    });
  }

  applyFilters(): void {
    let filteredChallenges = this.challenges;

    if (this.filterText) {
      filteredChallenges = filteredChallenges.filter(
        (challenge) =>
          challenge.title
            ?.toLowerCase()
            .includes(this.filterText.toLowerCase()) ||
          challenge.description
            ?.toLowerCase()
            .includes(this.filterText.toLowerCase()) ||
          challenge.technology?.name
            ?.toLowerCase()
            .includes(this.filterText.toLowerCase())
      );
    }

    if (this.filterDifficulty) {
      filteredChallenges = filteredChallenges.filter(
        (challenge) => challenge.difficulty === this.filterDifficulty
      );
    }

    this.totalPages = Math.ceil(filteredChallenges.length / this.itemsPerPage);
    this.totalPagesArray = Array.from(
      { length: this.totalPages },
      (_, i) => i + 1
    );
    this.paginateChallenges(filteredChallenges);
  }

  paginateChallenges(challenges: CodingChallenge[]): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedChallenges = challenges.slice(startIndex, endIndex);
  }

  changePage(page: number): void {
    if (page < 1 || page > this.totalPages) {
      return;
    }
    this.currentPage = page;
    this.applyFilters();
  }

  viewDetails(challengeId: number): void {
    // Navigate to the challenge details page
  }

  deleteChallenge(challengeId: number): void {
    this.challengeService.deleteCodingChallenge(challengeId).subscribe(() => {
      console.log('Challenge deleted successfully');
      this.fetchChallenges();
    });
  }
}
