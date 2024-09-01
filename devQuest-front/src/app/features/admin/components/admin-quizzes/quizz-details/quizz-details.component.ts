import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizzService } from '../../../services/quizz.service';
import { Question, Score, Technology } from '../../../models/admin-models';
import { TechnologyService } from '../../../services/technology.service';

@Component({
  selector: 'app-quizz-details',
  templateUrl: './quizz-details.component.html',
  styleUrl: './quizz-details.component.css',
})
export class QuizzDetailsComponent implements OnInit {
  quiz: any = {};
  questions: Question[] = [];
  technologies: Technology[] = [];
  scores: Score[] = [];

  constructor(
    private route: ActivatedRoute,
    private quizService: QuizzService,
    private router: Router,
    private techonologyService: TechnologyService
  ) {}

  ngOnInit(): void {
    const quizId = this.route.snapshot.paramMap.get('id');
    if (quizId) {
      this.loadQuizDetails(quizId);
      this.loadQuestions(quizId);
    } else {
      console.error('Quiz ID not found');
    }
    this.fetchTechnologies();
  }

  fetchTechnologies(): void {
    this.techonologyService.getAllTechnologies().subscribe(
      (data) => {
        this.technologies = data;
      },
      (error) => {
        console.error('Error loading technologies', error);
      }
    );
  }

  loadQuizDetails(quizId: string): void {
    this.quizService.getQuizzById(quizId).subscribe(
      (data) => {
        this.quiz = data;
      },
      (error) => {
        console.error('Error loading quiz details', error);
      }
    );
  }

  loadQuestions(quizId: string): void {
    this.quizService.getQuestionsByQuizzId(quizId).subscribe(
      (data) => {
        this.questions = data;
      },
      (error) => {
        console.error('Error loading questions', error);
      }
    );
  }

  editQuiz(): void {
    // Show the modal
    const modal = document.getElementById('editModal');
    if (modal) {
      modal.style.display = 'block';
    }
  }

  deleteQuiz(): void {
    // Show the modal
    const modal = document.getElementById('deleteModal');
    if (modal) {
      modal.style.display = 'block';
    }
  }

  confirmDelete(): void {
    if (this.quiz && this.quiz.id) {
      this.quizService.deleteQuizz(this.quiz.id).subscribe(
        (data) => {
          console.log('Quiz deleted successfully', data);
          this.closeModal();
          this.router.navigate(['/admin/quizz-list']);
        },
        (error) => {
          console.error('Error deleting quiz', error);
        }
      );
    } else {
      console.error('Quiz ID is not defined');
    }
  }

  closeModal(): void {
    const modal = document.getElementById('deleteModal');
    if (modal) {
      modal.style.display = 'none';
    }
  }

  closeEditModal(): void {
    const modal = document.getElementById('editModal');
    if (modal) {
      modal.style.display = 'none';
    }
  }

  saveChanges(): void {
    this.quizService.updateQuizz(this.quiz).subscribe(
      (data) => {
        console.log('Quiz updated successfully', data);
        this.closeEditModal();
      },
      (error) => {
        console.error('Error updating quiz', error);
      }
    );
  }

  viewScores(): void {
    this.quizService.getScoresByQuizId(this.quiz.id).subscribe((scores) => {
      this.scores = scores.sort((a: Score, b: Score) => {
        return b.score - a.score;
      });
      const modal = document.getElementById('scoresModal');
      if (modal) {
        modal.style.display = 'block';
      }
    });
  }

  closeScoresModal(): void {
    const modal = document.getElementById('scoresModal');
    if (modal) {
      modal.style.display = 'none';
    }
  }

  getCorrectAnswersCount(answers: any[]): number {
    return answers.filter((answer) => answer.correct).length;
  }
}
