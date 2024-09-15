import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizzesService } from '../../services/quizzes.service';
import {
  AnswerDTO,
  Question,
  Score,
} from '../../../../shared/models/usermodels';

@Component({
  selector: 'app-score',
  templateUrl: './score.component.html',
  styleUrls: ['./score.component.css'],
})
export class ScoreComponent implements OnInit {
  score: Score | null = null;
  questions: Question[] = [];
  answers: AnswerDTO[] | undefined = [];
  quizzId: number | null = null;
  scoreId: number | null = null;

  constructor(
    private quizzesService: QuizzesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.quizzId = +this.route.snapshot.paramMap.get('quizzId')!;
    this.scoreId = +this.route.snapshot.paramMap.get('scoreId')!;
    if (this.quizzId && this.scoreId) {
      this.loadScore(this.scoreId);
    }
  }

  loadScore(id: number): void {
    this.quizzesService.getScoreById(id).subscribe(
      (score) => {
        this.score = score;
        this.questions = score.quizz.questions;
        this.answers = score.answers;
      },
      (error) => {
        console.error('Error loading score:', error);
      }
    );
  }

  isCorrect(questionId: number | undefined, option: string): boolean {
    const answer = this.answers?.find((a) => a.question?.id === questionId);
    return answer
      ? answer.answer === option && answer.isCorrect === true
      : false;
  }

  isChosen(questionId: number | undefined, option: string): boolean {
    const answer = this.answers?.find((a) => a.question?.id === questionId);
    return answer ? answer.answer === option : false;
  }

  goBack(): void {
    this.router.navigate(['/user/quizzes']);
  }
}
