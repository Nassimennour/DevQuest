import { CommonModule, DatePipe } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminSidebarComponent } from './components/admin-sidebar/admin-sidebar.component';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { SharedModule } from '../../shared/shared.module';
import { RouterLink, RouterModule } from '@angular/router';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AdminReportsComponent } from './components/admin-reports/admin-reports.component';
import { provideHttpClient } from '@angular/common/http';
import {
  BaseChartDirective,
  provideCharts,
  withDefaultRegisterables,
} from 'ng2-charts';
import { QuizzChartComponent } from './components/quizz-chart/quizz-chart.component';
import { TechnologyChartComponent } from './components/technology-chart/technology-chart.component';
import { UserActivityChartComponent } from './components/user-activity-chart/user-activity-chart.component';
import { UsersListComponent } from './components/admin-users/users-list/users-list.component';
import { AddUserComponent } from './components/admin-users/add-user/add-user.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { EditUserModalComponent } from './components/admin-users/edit-user-modal/edit-user-modal.component';
import { UserDetailsComponent } from './components/admin-users/user-details/user-details.component';

@NgModule({
  declarations: [
    AdminDashboardComponent,
    AdminSidebarComponent,
    AdminNavbarComponent,
    AdminHomeComponent,
    AdminReportsComponent,
    QuizzChartComponent,
    TechnologyChartComponent,
    UserActivityChartComponent,
    UsersListComponent,
    AddUserComponent,
    EditUserModalComponent,
    UserDetailsComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule,
    RouterModule,
    BaseChartDirective,
    RouterLink,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [
    provideHttpClient(),
    provideCharts(withDefaultRegisterables()),
    DatePipe,
  ],
})
export class AdminModule {}
