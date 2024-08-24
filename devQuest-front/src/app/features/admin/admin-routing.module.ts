import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AdminQuizzesComponent } from './components/admin-quizzes/admin-quizzes.component';
import { AdminChallengesComponent } from './components/admin-challenges/admin-challenges.component';
import { AdminReportsComponent } from './components/admin-reports/admin-reports.component';
import { UsersListComponent } from './components/admin-users/users-list/users-list.component';
import { AddUserComponent } from './components/admin-users/add-user/add-user.component';
import { UserDetailsComponent } from './components/admin-users/user-details/user-details.component';

const routes: Routes = [
  {
    path: '',
    component: AdminDashboardComponent,
    children: [
      { path: 'home', component: AdminHomeComponent },
      { path: 'users-list', component: UsersListComponent },
      { path: 'user-details/:id', component: UserDetailsComponent },
      { path: 'add-user', component: AddUserComponent },
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
