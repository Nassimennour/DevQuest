import { Component, OnInit } from '@angular/core';
import { QuizzesService } from '../../services/quizzes.service';
import { UserService } from '../../services/user.service';
import { Quizz, Technology, Score } from '../../../../shared/models/usermodels';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quizzes',
  templateUrl: './quizzes.component.html',
  styleUrls: ['./quizzes.component.css'],
})
export class QuizzesComponent implements OnInit {
  quizzes: Quizz[] = [];
  filteredQuizzes: Quizz[] = [];
  paginatedQuizzes: Quizz[] = [];
  technologies: Technology[] = [];
  userScores: Score[] = [];
  searchTerm: string = '';
  selectedDifficulty: string = '';
  selectedTechnology: string = '';
  selectedDuration: string = '';
  sortOrder: string = 'title';
  currentPage: number = 1;
  itemsPerPage: number = 6;
  totalPages: number = 1;

  constructor(
    private quizzesService: QuizzesService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.fetchQuizzes();
    this.fetchTechnologies();
    this.fetchUserScores();
  }

  fetchQuizzes(): void {
    this.quizzesService.getAllQuizzes().subscribe(
      (quizzes) => {
        this.quizzes = quizzes;
        this.filteredQuizzes = quizzes;
        this.totalPages = Math.ceil(
          this.filteredQuizzes.length / this.itemsPerPage
        );
        this.paginateQuizzes();
      },
      (error) => {
        console.error('Error fetching quizzes:', error);
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

  fetchUserScores(): void {
    this.quizzesService.getMyScores().subscribe(
      (scores) => {
        this.userScores = scores;
      },
      (error) => {
        console.error('Error fetching user scores:', error);
      }
    );
  }

  filterQuizzes(): void {
    this.filteredQuizzes = this.quizzes;

    if (this.searchTerm) {
      this.filteredQuizzes = this.filteredQuizzes.filter((quiz) =>
        quiz.title?.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    }

    if (this.selectedDifficulty) {
      this.filteredQuizzes = this.filteredQuizzes.filter(
        (quiz) => quiz.difficulty === this.selectedDifficulty
      );
    }

    if (this.selectedTechnology) {
      this.filteredQuizzes = this.filteredQuizzes.filter(
        (quiz) => quiz.technology?.name === this.selectedTechnology
      );
    }

    if (this.selectedDuration) {
      this.filteredQuizzes = this.filteredQuizzes.filter((quiz) => {
        if (this.selectedDuration === 'short') {
          return quiz.duration! <= 30;
        } else if (this.selectedDuration === 'medium') {
          return quiz.duration! > 30 && quiz.duration! <= 60;
        } else if (this.selectedDuration === 'long') {
          return quiz.duration! > 60;
        }
        return true;
      });
    }

    this.totalPages = Math.ceil(
      this.filteredQuizzes.length / this.itemsPerPage
    );
    this.currentPage = 1;
    this.paginateQuizzes();
  }

  sortQuizzes(): void {
    this.filteredQuizzes.sort((a, b) => {
      if (this.sortOrder === 'title') {
        return a.title!.localeCompare(b.title!);
      } else if (this.sortOrder === 'difficulty') {
        return a.difficulty!.localeCompare(b.difficulty!);
      } else if (this.sortOrder === 'duration') {
        return a.duration! - b.duration!;
      }
      return 0;
    });
    this.paginateQuizzes();
  }

  paginateQuizzes(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedQuizzes = this.filteredQuizzes.slice(startIndex, endIndex);
  }

  previousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.paginateQuizzes();
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.paginateQuizzes();
    }
  }

  viewQuizDetails(quizId: number | undefined): void {
    this.router.navigate(['/user/quizz', quizId]);
  }

  takeQuiz(quizId: number | undefined): void {
    this.router.navigate(['/user/take-quizz', quizId]);
  }

  viewScore(quizId: number | undefined): void {
    const score = this.userScores.find((s) => s.quizz.id === quizId);
    if (score) {
      this.router.navigate(['/user/quizz', quizId, 'score', score.id]);
    }
  }

  hasTakenQuiz(quizId: number | undefined): boolean {
    return this.userScores.some((score) => score.quizz.id === quizId);
  }
}
