import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Solution, updateSolution, User } from '../../../models/admin-models';
import { ChallengeService } from '../../../services/challenge.service';
import { UsersService } from '../../../services/users.service';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-solution-details',
  templateUrl: './solution-details.component.html',
  styleUrls: ['./solution-details.component.css'],
})
export class SolutionDetailsComponent implements OnInit {
  @ViewChild('isCorrectSelect') isCorrectSelect!: ElementRef<HTMLSelectElement>;

  solutionId: string | null = null;
  solution: Solution | null = null;
  user!: User;
  highlightedCode: string = '';
  inputTriggered: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private challengeService: ChallengeService,
    private usersService: UsersService
  ) {}

  ngOnInit(): void {
    this.solutionId = this.route.snapshot.paramMap.get('id');
    this.fetchSolution();
  }

  fetchSolution(): void {
    this.challengeService
      .getSolutionById(this.solutionId as string)
      .subscribe((data) => {
        console.log('solution: ', data);
        this.solution = data;
        this.fetchUserDetails(this.solution?.user);
      });
  }

  fetchUserDetails(userId: number): void {
    this.usersService.getUserById(userId).subscribe((data) => {
      console.log('user data: ', data);
      this.user = data;
    });
  }

  updateIsCorrect(): void {
    if (this.solution && this.isCorrectSelect.nativeElement.value) {
      this.solution.isCorrect =
        this.isCorrectSelect.nativeElement.value === 'true';
      let solutionDTO: updateSolution = {
        id: this.solution.id,
        isCorrect: this.solution.isCorrect,
        code: this.solution.code,
        submissionDate: this.solution.submissionDate,
      };
      this.challengeService.updateSolution(solutionDTO).subscribe(
        (data) => {
          console.log('solution updated: ', data);
          this.solution = data;
        },
        (error) => {
          console.error('error updating solution: ', error);
        }
      );
    }
  }

  openDeleteModal(): void {
    const deleteModal = new Modal(
      document.getElementById('deleteModal') as HTMLElement
    );
    deleteModal.show();
  }

  deleteSolution(): void {
    if (this.solution?.id) {
      this.challengeService.deleteSolution(this.solution.id).subscribe(() => {
        this.router.navigate(['/admin/solutions']);
      });
    }
  }

  triggerChange(): void {
    this.inputTriggered = true;
  }
}
