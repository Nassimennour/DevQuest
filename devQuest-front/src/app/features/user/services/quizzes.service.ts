import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  Answer,
  Question,
  Quizz,
  Score,
} from '../../../shared/models/usermodels';

@Injectable({
  providedIn: 'root',
})
export class QuizzesService {
  BASE_API: string = environment.apiUrl;
  getAllQuizzesEndpoint: string = environment.userEndpoints.getAllQuizzes;
  getQuizzByIdEndpoint: string = environment.userEndpoints.getQuizzById;
  getQuestionsByQuizzIdEndpoint: string =
    environment.userEndpoints.getQuestionsByQuizzId;
  getScoresByQuizzIdEndpoint: string =
    environment.userEndpoints.getScoresByQuizzId;
  submitQuizzEndpoint: string = environment.userEndpoints.submitQuizz;
  getQuestionByIdEndpoint: string = environment.userEndpoints.getQuestionById;
  getScoreByIdEndpoint: string = environment.userEndpoints.getScoreById;
  getMyScoresEndpoint: string = environment.userEndpoints.getMyScores;

  constructor(private http: HttpClient) {}

  getAllQuizzes(): Observable<Quizz[]> {
    return this.http.get<Quizz[]>(
      `${this.BASE_API}${this.getAllQuizzesEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getQuizzById(id: number): Observable<Quizz> {
    return this.http.get<Quizz>(
      `${this.BASE_API}${this.getQuizzByIdEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  submitQuizz(data: Answer[], quizzId: number): Observable<Score> {
    return this.http.post<Score>(
      `${this.BASE_API}${this.submitQuizzEndpoint}${quizzId}`,
      data,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getQuestionsByQuizzId(id: number): Observable<Question[]> {
    return this.http.get<Question[]>(
      `${this.BASE_API}${this.getQuestionsByQuizzIdEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getScoresByQuizzId(id: number): Observable<Score[]> {
    return this.http.get<Score[]>(
      `${this.BASE_API}${this.getScoresByQuizzIdEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getScoreById(id: number): Observable<Score> {
    return this.http.get<Score>(
      `${this.BASE_API}${this.getScoreByIdEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getMyScores(): Observable<Score[]> {
    return this.http.get<Score[]>(
      `${this.BASE_API}${this.getMyScoresEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }
}
