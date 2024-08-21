import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AdminUsersComponent } from './components/admin-users/admin-users.component';
import { AdminQuizzesComponent } from './components/admin-quizzes/admin-quizzes.component';
import { AdminChallengesComponent } from './components/admin-challenges/admin-challenges.component';
import { AdminReportsComponent } from './components/admin-reports/admin-reports.component';

const routes: Routes = [
  {
    path: '',
    component: AdminDashboardComponent,
    children: [
      { path: 'home', component: AdminHomeComponent },
      { path: 'users', component: AdminUsersComponent },
      { path: 'quizzes', component: AdminQuizzesComponent },
      { path: 'coding-challenges', component: AdminChallengesComponent },
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
