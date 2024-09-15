import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { SharedModule } from '../../shared/shared.module';
import { UserNavComponent } from './components/user-nav/user-nav.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserLayoutComponent } from './components/user-layout/user-layout.component';
import { FooterComponent } from '../../shared/components/footer/footer.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { QuizzesComponent } from './components/quizzes/quizzes.component';
import { QuizzComponent } from './components/quizz/quizz.component';
import { CodingChallengesComponent } from './components/coding-challenges/coding-challenges.component';
import { ChallengeComponent } from './components/challenge/challenge.component';
import { RankingComponent } from './components/ranking/ranking.component';
import { TakeChallengeComponent } from './components/take-challenge/take-challenge.component';
import { TakeQuizzComponent } from './components/take-quizz/take-quizz.component';
import { SolutionComponent } from './components/solution/solution.component';
import { ScoreComponent } from './components/score/score.component';
import { ChallengeSolutionsComponent } from './components/challenge-solutions/challenge-solutions.component';

@NgModule({
  declarations: [
    UserDashboardComponent,
    UserNavComponent,
    UserProfileComponent,
    UserLayoutComponent,
    QuizzesComponent,
    QuizzComponent,
    CodingChallengesComponent,
    ChallengeComponent,
    RankingComponent,
    TakeChallengeComponent,
    TakeQuizzComponent,
    SolutionComponent,
    ScoreComponent,
    ChallengeSolutionsComponent,
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [FooterComponent],
})
export class UserModule {}
