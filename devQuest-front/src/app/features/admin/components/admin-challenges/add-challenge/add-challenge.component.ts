import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Technology } from '../../../models/admin-models';
import { ChallengeService } from '../../../services/challenge.service';
import { TechnologyService } from '../../../services/technology.service';
import { AuthService } from '../../../../../shared/services/auth.service';

@Component({
  selector: 'app-add-challenge',
  templateUrl: './add-challenge.component.html',
  styleUrls: ['./add-challenge.component.css'],
})
export class AddChallengeComponent implements OnInit {
  challengeForm!: FormGroup;
  submitted = false;
  technologies: Technology[] = [];
  creatorId!: number;

  constructor(
    private formBuilder: FormBuilder,
    private challengeService: ChallengeService,
    private technologyService: TechnologyService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.challengeForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      difficulty: ['', Validators.required],
      technologyId: ['', Validators.required],
      duration: ['', Validators.required],
    });

    this.technologyService.getAllTechnologies().subscribe((data) => {
      this.technologies = data;
    });
    this.authService.getUserProfile().subscribe(
      (data) => {
        this.creatorId = data.id;
        console.log('Creator ID:', this.creatorId);
      },
      (error) => {
        console.error('Error getting creator ID:', error);
      }
    );
  }

  get f() {
    return this.challengeForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.challengeForm.invalid) {
      return;
    }

    const challengeData = {
      ...this.challengeForm.value,
      creatorId: this.creatorId,
      creationDate: new Date().toISOString(),
    };

    this.challengeService.createCodingChallenge(challengeData).subscribe(
      () => {
        console.log('Challenge created successfully');
        this.router.navigate(['/challenge-list']);
      },
      (error) => {
        console.error('Error creating challenge:', error);
      }
    );
  }

  onReset(): void {
    this.submitted = false;
    this.challengeForm.reset();
  }
}
