import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import { Ranking } from '../models/admin-models';

@Injectable({
  providedIn: 'root',
})
export class RankingService {
  BASE_API = environment.apiUrl;
  getRankingByUserIdEndpoint = environment.endpoints.getRankingByUserIdAdmin;

  constructor(private httpClient: HttpClient) {}

  getRankingByUserId(userId: number): Observable<Ranking> {
    return this.httpClient.get<Ranking>(
      `${this.BASE_API}${this.getRankingByUserIdEndpoint}${userId}`,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }
}
