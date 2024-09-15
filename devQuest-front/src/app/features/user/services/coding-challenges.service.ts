import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  CodingChallenge,
  Solution,
  SolutionDTO,
  UpdateSolutionDTO,
} from '../../../shared/models/usermodels';

@Injectable({
  providedIn: 'root',
})
export class CodingChallengesService {
  BASE_API: string = environment.apiUrl;
  getAllChallengesEndpoint: string =
    environment.userEndpoints.getAllCodingChallenges;
  getChallengeByIdEndpoint: string =
    environment.userEndpoints.getCodingChallengeById;
  saveSolutionEndpoint: string = environment.userEndpoints.saveSolution;
  getSolutionEndpoint: string = environment.userEndpoints.getSolutionById;
  getAllSolutionsByChallengeIdEndpoint: string =
    environment.userEndpoints.getSolutionsByCodingChallengeId;
  getMySolutionsEndpoint: string = environment.userEndpoints.getMySolutions;
  updateSolutionEndpoint: string = environment.userEndpoints.updateSolution;

  constructor(private http: HttpClient) {}

  getAllChallenges(): Observable<CodingChallenge[]> {
    return this.http.get<CodingChallenge[]>(
      `${this.BASE_API}${this.getAllChallengesEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getCodingChallengeById(id: number): Observable<CodingChallenge> {
    return this.http.get<CodingChallenge>(
      `${this.BASE_API}${this.getChallengeByIdEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getSolutionById(id: number): Observable<Solution> {
    return this.http.get<Solution>(
      `${this.BASE_API}${this.getSolutionEndpoint}${id}`,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }

  getAllSolutionsByChallengeId(challengeId: number): Observable<Solution[]> {
    return this.http.get<Solution[]>(
      `${this.BASE_API}${this.getAllSolutionsByChallengeIdEndpoint}${challengeId}`,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }

  getMySolutions(): Observable<Solution[]> {
    return this.http.get<Solution[]>(
      `${this.BASE_API}${this.getMySolutionsEndpoint}`,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }

  saveSolution(solutionData: SolutionDTO): Observable<Solution> {
    return this.http.post<Solution>(
      `${this.BASE_API}${this.saveSolutionEndpoint}`,
      solutionData,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }

  updateSolution(solutionData: UpdateSolutionDTO): Observable<Solution> {
    return this.http.put<Solution>(
      `${this.BASE_API}${this.updateSolutionEndpoint}`,
      solutionData,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }
}
