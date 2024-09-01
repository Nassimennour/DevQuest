import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import { CodingChallenge, CodingChallengeDTO } from '../models/admin-models';

@Injectable({
  providedIn: 'root',
})
export class ChallengeService {
  BASE_API: string = environment.apiUrl;
  // Challenge endpoints
  createChallengeEndpoint: string =
    environment.endpoints.createCodingChallengeAdmin;
  updateChallengeEndpoint: string =
    environment.endpoints.updateCodingChallengeAdmin;
  deleteChallengeEndpoint: string =
    environment.endpoints.deleteCodingChallengeAdmin;
  getChallengesEndpoint: string =
    environment.endpoints.getCodingChallengesAdmin;
  getChallengeByIdEndpoint: string =
    environment.endpoints.getCodingChallengeByIdAdmin;

  constructor(private http: HttpClient) {}

  getAllChallenges(): Observable<CodingChallenge[]> {
    return this.http.get<CodingChallenge[]>(
      this.BASE_API + this.getChallengesEndpoint,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getCodingChallengeById(challengeId: string): Observable<CodingChallenge> {
    return this.http.get<CodingChallenge>(
      this.BASE_API + this.getChallengeByIdEndpoint + challengeId,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  createCodingChallenge(
    challengeData: CodingChallengeDTO
  ): Observable<CodingChallenge> {
    return this.http.post<CodingChallenge>(
      this.BASE_API + this.createChallengeEndpoint,
      challengeData,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  updateCodingChallenge(
    challengeData: CodingChallenge
  ): Observable<CodingChallenge> {
    return this.http.put<CodingChallenge>(
      this.BASE_API + this.updateChallengeEndpoint,
      challengeData,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  deleteCodingChallenge(challengeId: number): Observable<any> {
    return this.http.delete(
      this.BASE_API + this.deleteChallengeEndpoint + challengeId,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
          responseType: 'text' as 'json',
        },
      }
    );
  }
}
