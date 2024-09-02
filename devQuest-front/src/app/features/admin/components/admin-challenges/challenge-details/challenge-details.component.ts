import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChallengeService } from '../../../services/challenge.service';
import { TechnologyService } from '../../../services/technology.service';
import { CodingChallenge, Solution } from '../../../models/admin-models';

@Component({
  selector: 'app-challenge-details',
  templateUrl: './challenge-details.component.html',
  styleUrls: ['./challenge-details.component.css'],
})
export class ChallengeDetailsComponent implements OnInit {
  challenge!: CodingChallenge;
  solutions: Solution[] = [];
  paginatedSolutions: Solution[] = [];
  technologies: any[] = [];
  editForm!: FormGroup;
  currentPage = 1;
  itemsPerPage = 5;
  totalPages = 0;
  totalPagesArray: number[] = [];
  challengeId: string | null = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private challengeService: ChallengeService,
    private technologyService: TechnologyService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.challengeId = this.route.snapshot.paramMap.get('id');
    this.fetchChallenge();
    this.fetchTechnologies();
    this.editForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      technology: ['', Validators.required],
      difficulty: ['', Validators.required],
      duration: ['', Validators.required],
    });
  }

  fetchChallenge(): void {
    this.challengeService
      .getCodingChallengeById(this.challengeId as string)
      .subscribe((data) => {
        this.challenge = data;
        this.editForm.patchValue({
          title: this.challenge.title,
          description: this.challenge.description,
          technology: this.challenge.technology,
          difficulty: this.challenge.difficulty,
          duration: this.challenge.duration,
        });
        this.fetchSolutions(this.challengeId as string);
      });
  }

  fetchTechnologies(): void {
    this.technologyService.getAllTechnologies().subscribe((data) => {
      this.technologies = data;
    });
  }
  applyPagination(): void {
    this.totalPages = Math.ceil(this.solutions.length / this.itemsPerPage);
    this.totalPagesArray = Array.from(
      { length: this.totalPages },
      (_, i) => i + 1
    );
    this.paginateSolutions();
  }

  fetchSolutions(challengeId: string): void {
    this.challengeService
      .getSolutionsByChallengeId(challengeId)
      .subscribe((data) => {
        this.solutions = data.sort((a, b) => {
          const aDate = a.submissionDate ? new Date(a.submissionDate) : null;
          const bDate = b.submissionDate ? new Date(b.submissionDate) : null;
          return (bDate?.getTime() || 0) - (aDate?.getTime() || 0);
        });
        this.applyPagination();
      });
  }

  paginateSolutions(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedSolutions = this.solutions.slice(startIndex, endIndex);
  }

  changePage(page: number): void {
    if (page < 1 || page > this.totalPages) {
      return;
    }
    this.currentPage = page;
    this.paginateSolutions();
  }
  onSubmit(): void {
    if (this.editForm.invalid) {
      return;
    }

    const updatedChallenge = {
      ...this.challenge,
      ...this.editForm.value,
    };

    this.challengeService.updateCodingChallenge(updatedChallenge).subscribe(
      () => {
        this.router.navigate(['/challenges']);
      },
      (error) => {
        console.error('Error updating challenge:', error);
      }
    );
  }

  openEditModal(): void {
    const modal = document.getElementById('editModal');
    if (modal) {
      modal.style.display = 'block';
    }
  }

  openDeleteModal(): void {
    const modal = document.getElementById('deleteModal');
    if (modal) {
      modal.style.display = 'block';
    }
  }

  deleteSolution(solutionId: any): void {
    solutionId = solutionId as number;
    this.challengeService.deleteSolution(solutionId).subscribe(() => {
      this.fetchChallenge();
    });
  }

  closeEditModal(): void {
    const modal = document.getElementById('editModal');
    if (modal) {
      modal.style.display = 'none';
    }
  }

  closeDeleteModal(): void {
    const modal = document.getElementById('deleteModal');
    if (modal) {
      modal.style.display = 'none';
    }
  }

  confirmDelete(): void {
    this.challengeService
      .deleteCodingChallenge(this.challenge.id as number)
      .subscribe(() => {
        console.log('Challenge deleted');
        this.router.navigate(['/challenge-list']);
      });
  }

  onEditSubmit(): void {
    if (this.editForm.invalid) {
      return;
    }

    const updatedChallenge = {
      ...this.editForm.value,
      id: this.challenge.id,
    };
    console.log('Updated challenge DTO:', updatedChallenge);
    this.challengeService.updateCodingChallenge(updatedChallenge).subscribe(
      (data) => {
        console.log('Challenge updated:', data);
        this.fetchChallenge();
      },
      (error) => {
        console.error('Error updating challenge:', error);
      }
    );
  }
}
