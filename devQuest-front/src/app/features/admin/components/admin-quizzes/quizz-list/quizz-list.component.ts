import { Component, OnInit } from '@angular/core';
import { QuizzService } from '../../../services/quizz.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quizz-list',
  templateUrl: './quizz-list.component.html',
  styleUrl: './quizz-list.component.css',
})
export class QuizzListComponent implements OnInit {
  quizzes: any[] = [];
  filteredQuizzes: any[] = [];
  currentPage = 1;
  totalPages = 1;
  itemsPerPage = 10;
  pages: number[] = [];

  constructor(private quizService: QuizzService, private router: Router) {}

  ngOnInit(): void {
    this.loadQuizzes();
  }

  loadQuizzes(): void {
    this.quizService.getAllQuizzes().subscribe((data) => {
      this.quizzes = data;
      this.filteredQuizzes = this.paginate(
        this.quizzes,
        this.currentPage,
        this.itemsPerPage
      );
      this.totalPages = Math.ceil(this.quizzes.length / this.itemsPerPage);
      this.pages = Array.from({ length: this.totalPages }, (_, i) => i + 1); // Generate an array of page numbers
      console.log(this.pages);
    });
  }

  onSearch(event: any): void {
    const query = event.target.value.toLowerCase();
    this.filteredQuizzes = this.quizzes.filter(
      (quiz) =>
        quiz.title.toLowerCase().includes(query) ||
        quiz.overview.toLowerCase().includes(query)
    );
  }

  onFilterChange(event: any): void {
    const difficulty = event.target.value;
    this.filteredQuizzes = difficulty
      ? this.quizzes.filter((quiz) => quiz.difficulty === difficulty)
      : this.quizzes;
  }

  viewQuizDetails(quizId: number): void {
    this.router.navigate(['/admin/quizz-details', quizId]);
  }

  editQuiz(quizId: number): void {
    // Implement navigation to the quiz edit page
  }

  deleteQuiz(quizId: number): void {
    const id = quizId.toString();
    this.quizService.deleteQuizz(id).subscribe(
      () => {
        this.loadQuizzes();
        console.log('Quiz deleted successfully: ', id);
      },
      (error) => {
        console.error('Error deleting quiz', error);
      }
    );
  }

  paginate(items: any[], page: number, itemsPerPage: number): any[] {
    const start = (page - 1) * itemsPerPage;
    return items.slice(start, start + itemsPerPage);
  }

  goToPage(page: number): void {
    this.currentPage = page;
    this.filteredQuizzes = this.paginate(
      this.quizzes,
      this.currentPage,
      this.itemsPerPage
    );
  }

  previousPage(): void {
    if (this.currentPage > 1) {
      this.goToPage(this.currentPage - 1);
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.goToPage(this.currentPage + 1);
    }
  }
}
