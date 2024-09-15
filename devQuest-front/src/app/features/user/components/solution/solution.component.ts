import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CodingChallengesService } from '../../services/coding-challenges.service';
import {
  CodingChallenge,
  Solution,
} from '../../../../shared/models/usermodels';
import * as Prism from 'prismjs';
import 'prismjs/components/prism-java';
import 'prismjs/components/prism-javascript';
import 'prismjs/components/prism-python';
import 'prismjs/components/prism-typescript';
import 'prismjs/components/prism-csharp';
import 'prismjs/components/prism-c';
import 'prismjs/components/prism-cpp';
import 'prismjs/components/prism-ruby';
import 'prismjs/components/prism-swift';
import 'prismjs/components/prism-markup';
import 'prismjs/components/prism-css';
import 'prismjs/components/prism-scss';

@Component({
  selector: 'app-solution',
  templateUrl: './solution.component.html',
  styleUrls: ['./solution.component.css'],
})
export class SolutionComponent implements OnInit {
  challenge: CodingChallenge | null = null;
  solution: Solution | null = null;
  highlightedCode: string = '';

  constructor(
    private challengesService: CodingChallengesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const challengeId = this.route.snapshot.paramMap.get('challengeId');
    const solutionId = this.route.snapshot.paramMap.get('solutionId');
    if (challengeId && solutionId) {
      this.fetchChallengeDetails(+challengeId);
      this.fetchSolutionDetails(+solutionId);
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

  fetchSolutionDetails(id: number): void {
    this.challengesService.getSolutionById(id).subscribe(
      (solution) => {
        this.solution = solution;
        this.highlightCode();
      },
      (error) => {
        console.error('Error fetching solution details:', error);
      }
    );
  }

  highlightCode(): void {
    if (this.solution && this.challenge && this.solution.code) {
      const language = this.challenge.technology?.name.toLowerCase();
      if (Prism.languages[language]) {
        this.highlightedCode = Prism.highlight(
          this.solution.code,
          Prism.languages[language],
          language
        );
      } else {
        console.warn(
          `Language ${language} not found, falling back to plain text.`
        );
        this.highlightedCode = Prism.highlight(
          this.solution.code,
          Prism.languages['plaintext'],
          'plaintext'
        );
      }
    }
  }

  goBack(): void {
    this.router.navigate(['/user/coding-challenges']);
  }

  viewOtherSolutions(): void {
    this.router.navigate(['/user/challenge/solutions', this.challenge?.id]);
  }
}
