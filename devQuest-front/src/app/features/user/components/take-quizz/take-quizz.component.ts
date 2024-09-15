import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizzesService } from '../../services/quizzes.service';

import { Answer, Question, Quizz } from '../../../../shared/models/usermodels';

@Component({
  selector: 'app-take-quizz',
  templateUrl: './take-quizz.component.html',
  styleUrls: ['./take-quizz.component.css'],
})
export class TakeQuizzComponent implements OnInit {
  quizz: Quizz | null = null;
  questions: Question[] = [];
  answers: Answer[] = [];
  quizzId: number | null = null;

  constructor(
    private quizzesService: QuizzesService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.quizzId = +this.route.snapshot.paramMap.get('id')!;
    if (this.quizzId) {
      this.loadQuizz(this.quizzId);
      this.loadQuestions(this.quizzId);
    }
  }

  loadQuizz(id: number): void {
    this.quizzesService.getQuizzById(id).subscribe(
      (quizz) => {
        this.quizz = quizz;
      },
      (error) => {
        console.error('Error loading quizz:', error);
      }
    );
  }

  loadQuestions(id: number): void {
    this.quizzesService.getQuestionsByQuizzId(id).subscribe(
      (questions) => {
        this.questions = questions;
        this.answers = questions.map((question) => ({
          questionId: question.id,
          answer: '',
        }));
      },
      (error) => {
        console.error('Error loading questions:', error);
      }
    );
  }

  selectAnswer(questionId: number | undefined, answer: string): void {
    const answerObj = this.answers.find((a) => a.questionId === questionId);
    if (answerObj) {
      answerObj.answer = answer;
    }
  }

  isSelected(questionId: number | undefined, option: string): boolean {
    const answerObj = this.answers.find((a) => a.questionId === questionId);
    return answerObj ? answerObj.answer === option : false;
  }

  submitQuizz(): void {
    if (confirm('Are you sure you want to submit your answers?')) {
      this.quizzesService.submitQuizz(this.answers, this.quizzId!).subscribe(
        (score) => {
          this.router.navigate([
            `/user/quizz/${this.quizzId}/score/${score.id}`,
          ]);
        },
        (error) => {
          console.error('Error submitting quizz:', error);
        }
      );
    }
  }

  goBack(): void {
    this.router.navigate(['/user/quizzes']);
  }
}
