import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Question, Score } from '../models/admin-models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class QuizzService {
  BASE_API = environment.apiUrl;
  createQuizzEndpoint = environment.endpoints.createQuizzAdmin;
  createQuestionEndpoint = environment.endpoints.addQuestionAdmin;
  deleteQuestionEndpoint = environment.endpoints.deleteQuestionAdmin;
  updateQuestionEndpoint = environment.endpoints.updateQuestionAdmin;
  getAllQuizzesEndpoint = environment.endpoints.getAllQuizzesAdmin;
  getQuizzByIdEndpoint = environment.endpoints.getQuizzByIdAdmin;
  getQuizzesByTechnologyIdEndpoint =
    environment.endpoints.getQuizzesByTechnologyIdAdmin;
  getQuestionsByQuizIdEndpoint =
    environment.endpoints.getQuestionsByQuizIdAdmin;
  deleteQuizzEndpoint = environment.endpoints.deleteQuizzAdmin;
  updateQuizzEndpoint = environment.endpoints.updateQuizzAdmin;
  getScoresByQuizIdEndpoint = environment.endpoints.getScoresByQuizzIdAdmin;

  constructor(private http: HttpClient) {}

  createQuizz(quizzData: any) {
    return this.http.post(this.BASE_API + this.createQuizzEndpoint, quizzData, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    });
  }

  addQuestion(questionData: Question) {
    return this.http.post(
      this.BASE_API + this.createQuestionEndpoint,
      questionData,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  deleteQuestion(questionId: number) {
    return this.http.delete(
      this.BASE_API + this.deleteQuestionEndpoint + questionId,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  updateQuestion(questionData: Question): Observable<any> {
    return this.http.put(
      this.BASE_API + this.updateQuestionEndpoint,
      questionData,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }

  getAllQuizzes(): Observable<any> {
    return this.http.get(this.BASE_API + this.getAllQuizzesEndpoint, {
      headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
    });
  }

  getQuizzById(quizId: string): Observable<any> {
    return this.http.get(this.BASE_API + this.getQuizzByIdEndpoint + quizId, {
      headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
    });
  }

  getQuizzesByTechnologyId(technologyId: number): Observable<any> {
    return this.http.get(
      this.BASE_API + this.getQuizzesByTechnologyIdEndpoint + technologyId,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }

  getQuestionsByQuizzId(quizId: string): Observable<Question[]> {
    return this.http.get<Question[]>(
      this.BASE_API + this.getQuestionsByQuizIdEndpoint + quizId,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }

  deleteQuizz(quizId: string): Observable<string> {
    return this.http.delete(this.BASE_API + this.deleteQuizzEndpoint + quizId, {
      responseType: 'text',
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    });
  }

  updateQuizz(quizzData: any): Observable<any> {
    return this.http.put(this.BASE_API + this.updateQuizzEndpoint, quizzData, {
      headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
    });
  }

  getScoresByQuizId(quizId: string): Observable<Score[]> {
    return this.http.get<Score[]>(
      this.BASE_API + this.getScoresByQuizIdEndpoint + quizId,
      {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` },
      }
    );
  }
}
