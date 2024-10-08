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
import { QuizzListComponent } from './components/admin-quizzes/quizz-list/quizz-list.component';
import { AddQuizzComponent } from './components/admin-quizzes/add-quizz/add-quizz.component';
import { QuizzDetailsComponent } from './components/admin-quizzes/quizz-details/quizz-details.component';
import { AddChallengeComponent } from './components/admin-challenges/add-challenge/add-challenge.component';
import { ChallengeListComponent } from './components/admin-challenges/challenge-list/challenge-list.component';
import { ChallengeDetailsComponent } from './components/admin-challenges/challenge-details/challenge-details.component';
import { SolutionDetailsComponent } from './components/admin-challenges/solution-details/solution-details.component';
import { TechnologyListComponent } from './components/admin-technologies/technology-list/technology-list.component';
import { AddTechnologyComponent } from './components/admin-technologies/add-technology/add-technology.component';
import { TechnologyDetailsComponent } from './components/admin-technologies/technology-details/technology-details.component';
import { AddCategoryComponent } from './components/admin-technologies/add-category/add-category.component';
import { CategoryListComponent } from './components/admin-technologies/category-list/category-list.component';

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
    QuizzListComponent,
    AddQuizzComponent,
    QuizzDetailsComponent,
    AddChallengeComponent,
    ChallengeListComponent,
    ChallengeDetailsComponent,
    SolutionDetailsComponent,
    TechnologyListComponent,
    AddTechnologyComponent,
    TechnologyDetailsComponent,
    AddCategoryComponent,
    CategoryListComponent,
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
    CommonModule,
  ],
  providers: [
    provideHttpClient(),
    provideCharts(withDefaultRegisterables()),
    DatePipe,
  ],
})
export class AdminModule {}
