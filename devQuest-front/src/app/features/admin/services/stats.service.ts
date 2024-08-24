import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import {
  Activity,
  AdminStats,
  QuizzCompletionStats,
  technologyPopularity,
  UserActivity,
} from '../models/admin-models';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class StatsService {
  BASE_URL = environment.apiUrl;
  statsEndpoint = environment.endpoints.getAdminStats;
  quizzCompllStatsEndpoint = environment.endpoints.getQuizzCompletionStats;
  technologyPopularityEndpoint = environment.endpoints.getTechnologyPopularity;
  recentActivitiesEndpoint = environment.endpoints.getRecentActivities;
  userActivityEndpoint = environment.endpoints.getUserActivity;

  adminStats$!: Observable<AdminStats>;
  quizzCompletionStats$!: Observable<QuizzCompletionStats[]>;
  technologyPopularity$!: Observable<technologyPopularity[]>;
  recentActivities$!: Observable<Activity[]>;
  userActivity$!: Observable<UserActivity[]>;

  constructor(private http: HttpClient) {}

  getAdminStats(): Observable<AdminStats> {
    return (this.adminStats$ = this.http.get<AdminStats>(
      `${this.BASE_URL}${this.statsEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    ));
  }

  getQuizzCompletionStats(): Observable<QuizzCompletionStats[]> {
    return (this.quizzCompletionStats$ = this.http.get<QuizzCompletionStats[]>(
      `${this.BASE_URL}${this.quizzCompllStatsEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    ));
  }

  getTechnologyPopularityStats(): Observable<technologyPopularity[]> {
    return (this.technologyPopularity$ = this.http.get<technologyPopularity[]>(
      `${this.BASE_URL}${this.technologyPopularityEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    ));
  }

  getRecentActivities(): Observable<Activity[]> {
    return (this.recentActivities$ = this.http.get<Activity[]>(
      `${this.BASE_URL}${this.recentActivitiesEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    ));
  }

  getUserActivity(): Observable<UserActivity[]> {
    return (this.userActivity$ = this.http.get<UserActivity[]>(
      `${this.BASE_URL}${this.userActivityEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    ));
  }
}
