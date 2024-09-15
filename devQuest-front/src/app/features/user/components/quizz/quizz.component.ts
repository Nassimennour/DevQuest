import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizzesService } from '../../services/quizzes.service';
import { Quizz, Score } from '../../../../shared/models/usermodels';

@Component({
  selector: 'app-quizz',
  templateUrl: './quizz.component.html',
  styleUrls: ['./quizz.component.css'],
})
export class QuizzComponent implements OnInit {
  quizz: Quizz | null = null;
  topScores: Score[] = [];

  constructor(
    private quizzesService: QuizzesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const quizzId = this.route.snapshot.paramMap.get('id');
    if (quizzId) {
      this.fetchQuizzDetails(+quizzId);
      this.fetchTopScores(+quizzId);
    }
  }

  fetchQuizzDetails(id: number): void {
    this.quizzesService.getQuizzById(id).subscribe(
      (quizz) => {
        this.quizz = quizz;
      },
      (error) => {
        console.error('Error fetching quizz details:', error);
      }
    );
  }

  fetchTopScores(id: number): void {
    this.quizzesService.getScoresByQuizzId(id).subscribe(
      (scores) => {
        this.topScores = scores.sort((a, b) => b.score! - a.score!).slice(0, 5);
      },
      (error) => {
        console.error('Error fetching top scores:', error);
      }
    );
  }

  startQuiz(): void {
    this.router.navigate(['/take-quizz', this.quizz?.id]);
  }
}
