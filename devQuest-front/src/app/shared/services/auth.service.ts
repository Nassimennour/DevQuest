import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { RegistrationRequest } from '../models/usermodels';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  base_api: string = environment.apiUrl;
  registerEndpoint: string = environment.endpoints.register;
  token: string | null = null;

  constructor(private httpClient: HttpClient) {}

  registerUser(userData: RegistrationRequest): Observable<any> {
    return this.httpClient
      .post(this.base_api + this.registerEndpoint, userData)
      .pipe(tap((response) => this.handleResponse(response)));
  }

  handleResponse(response: any) {
    if (response) {
      localStorage.setItem('token', response);
      console.log('Token: ', response);
      this.token = response;
    }
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }
}
