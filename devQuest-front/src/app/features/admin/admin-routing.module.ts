import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AdminReportsComponent } from './components/admin-reports/admin-reports.component';
import { UsersListComponent } from './components/admin-users/users-list/users-list.component';
import { AddUserComponent } from './components/admin-users/add-user/add-user.component';
import { UserDetailsComponent } from './components/admin-users/user-details/user-details.component';
import { QuizzListComponent } from './components/admin-quizzes/quizz-list/quizz-list.component';
import { AddQuizzComponent } from './components/admin-quizzes/add-quizz/add-quizz.component';
import { QuizzDetailsComponent } from './components/admin-quizzes/quizz-details/quizz-details.component';
import { AddChallengeComponent } from './components/admin-challenges/add-challenge/add-challenge.component';
import { ChallengeListComponent } from './components/admin-challenges/challenge-list/challenge-list.component';
import { ChallengeDetailsComponent } from './components/admin-challenges/challenge-details/challenge-details.component';
import { SolutionDetailsComponent } from './components/admin-challenges/solution-details/solution-details.component';
import { TechnologyListComponent } from './components/admin-technologies/technology-list/technology-list.component';
import { TechnologyDetailsComponent } from './components/admin-technologies/technology-details/technology-details.component';
import { AddTechnologyComponent } from './components/admin-technologies/add-technology/add-technology.component';
import { AddCategoryComponent } from './components/admin-technologies/add-category/add-category.component';
import { CategoryListComponent } from './components/admin-technologies/category-list/category-list.component';

const routes: Routes = [
  {
    path: '',
    component: AdminDashboardComponent,
    children: [
      { path: 'home', component: AdminHomeComponent },
      { path: 'users-list', component: UsersListComponent },
      { path: 'user-details/:id', component: UserDetailsComponent },
      { path: 'add-user', component: AddUserComponent },
      { path: 'quizz-list', component: QuizzListComponent },
      { path: 'add-quizz', component: AddQuizzComponent },
      { path: 'quizz-details/:id', component: QuizzDetailsComponent },
      { path: 'add-challenge', component: AddChallengeComponent },
      { path: 'challenge-list', component: ChallengeListComponent },
      { path: 'challenge-details/:id', component: ChallengeDetailsComponent },
      { path: 'technology-list', component: TechnologyListComponent },
      { path: 'technology-details/:id', component: TechnologyDetailsComponent },
      { path: 'add-technology', component: AddTechnologyComponent },
      { path: 'add-category', component: AddCategoryComponent },
      { path: 'category-list', component: CategoryListComponent },
      { path: 'solution/:id', component: SolutionDetailsComponent },
      { path: 'reports', component: AdminReportsComponent },
      { path: '', redirectTo: 'home', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
