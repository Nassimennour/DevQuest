// technology-details.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TechnologyService } from '../../../services/technology.service';
import {
  CodingChallenge,
  QuizzDTO,
  Resource,
  Technology,
} from '../../../models/admin-models';
import { QuizzService } from '../../../services/quizz.service';
import { ChallengeService } from '../../../services/challenge.service';
import { ResourcesService } from '../../../services/resources.service';

@Component({
  selector: 'app-technology-details',
  templateUrl: './technology-details.component.html',
  styleUrls: ['./technology-details.component.css'],
})
export class TechnologyDetailsComponent implements OnInit {
  technology!: Technology;
  quizzes: QuizzDTO[] = [];
  challenges: CodingChallenge[] = [];
  resources: Resource[] = [];

  constructor(
    private route: ActivatedRoute,
    private technologyService: TechnologyService,
    private quizzService: QuizzService,
    private challengeService: ChallengeService,
    private resourceService: ResourcesService
  ) {}

  ngOnInit(): void {
    const technologyId = +this.route.snapshot.paramMap.get('id')!;
    this.loadTechnology(technologyId);
    this.loadQuizzes(technologyId);
    this.loadChallenges(technologyId);
    this.loadResources(technologyId);
  }

  loadTechnology(id: number): void {
    this.technologyService.getTechnologyById(id).subscribe(
      (response) => {
        this.technology = response;
      },
      (error) => {
        console.error('Error fetching technology:', error);
      }
    );
  }

  loadQuizzes(technologyId: number): void {
    this.quizzService.getQuizzesByTechnologyId(technologyId).subscribe(
      (response) => {
        this.quizzes = response;
      },
      (error) => {
        console.error('Error fetching quizzes:', error);
      }
    );
  }

  loadChallenges(technologyId: number): void {
    this.challengeService
      .getCodingChallengesByTechnologyId(technologyId)
      .subscribe(
        (response) => {
          this.challenges = response;
        },
        (error) => {
          console.error('Error fetching challenges:', error);
        }
      );
  }

  loadResources(technologyId: number): void {
    this.resourceService.getResourcesByTechnologyId(technologyId).subscribe(
      (response) => {
        this.resources = response;
      },
      (error) => {
        console.error('Error fetching resources:', error);
      }
    );
  }
}
