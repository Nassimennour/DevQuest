import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminSidebarComponent } from './components/admin-sidebar/admin-sidebar.component';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule } from '@angular/router';
import { MaterialModule } from '../../material/material.module';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AdminUsersComponent } from './components/admin-users/admin-users.component';
import { AdminQuizzesComponent } from './components/admin-quizzes/admin-quizzes.component';
import { AdminChallengesComponent } from './components/admin-challenges/admin-challenges.component';
import { AdminReportsComponent } from './components/admin-reports/admin-reports.component';

@NgModule({
  declarations: [
    AdminDashboardComponent,
    AdminSidebarComponent,
    AdminNavbarComponent,
    AdminHomeComponent,
    AdminUsersComponent,
    AdminQuizzesComponent,
    AdminChallengesComponent,
    AdminReportsComponent,
  ],
  imports: [CommonModule, AdminRoutingModule, SharedModule, RouterModule],
})
export class AdminModule {}
