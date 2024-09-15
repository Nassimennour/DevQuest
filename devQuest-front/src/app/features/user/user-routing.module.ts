import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserLayoutComponent } from './components/user-layout/user-layout.component';
import { QuizzesComponent } from './components/quizzes/quizzes.component';
import { CodingChallengesComponent } from './components/coding-challenges/coding-challenges.component';
import { QuizzComponent } from './components/quizz/quizz.component';
import { ChallengeComponent } from './components/challenge/challenge.component';
import { RankingComponent } from './components/ranking/ranking.component';
import { TakeQuizzComponent } from './components/take-quizz/take-quizz.component';
import { TakeChallengeComponent } from './components/take-challenge/take-challenge.component';
import { SolutionComponent } from './components/solution/solution.component';
import { ScoreComponent } from './components/score/score.component';
import { ChallengeSolutionsComponent } from './components/challenge-solutions/challenge-solutions.component';

const routes: Routes = [
  {
    path: '',
    component: UserLayoutComponent,
    children: [
      {
        path: 'profile',
        component: UserProfileComponent,
      },
      {
        path: 'dashboard',
        component: UserDashboardComponent,
      },
      {
        path: 'quizzes',
        component: QuizzesComponent,
      },
      {
        path: 'coding-challenges',
        component: CodingChallengesComponent,
      },
      {
        path: 'quizz/:id',
        component: QuizzComponent,
      },
      {
        path: 'coding-challenge/:id',
        component: ChallengeComponent,
      },
      {
        path: 'ranking',
        component: RankingComponent,
      },
      {
        path: 'take-quizz/:id',
        component: TakeQuizzComponent,
      },
      {
        path: 'take-challenge/:id',
        component: TakeChallengeComponent,
      },
      {
        path: 'challenge/:challengeId/solution/:solutionId',
        component: SolutionComponent,
      },
      {
        path: 'quizz/:quizzId/score/:scoreId',
        component: ScoreComponent,
      },
      {
        path: 'challenge/solutions/:challengeId',
        component: ChallengeSolutionsComponent,
      },

      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}
