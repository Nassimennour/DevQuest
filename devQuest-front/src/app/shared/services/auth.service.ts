import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';
import {
  AuthenticationRequest,
  RegistrationRequest,
  UserProfile,
} from '../models/usermodels';
import { Observable, tap, catchError, map, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // endpoints
  base_api: string = environment.apiUrl;
  registerEndpoint: string = environment.endpoints.register;
  loginEndpoint: string = environment.endpoints.login;
  myProfileEndpoint: string = environment.endpoints.getMyProfile;

  token: string | null = null;
  userRole: string | null = null;
  loggedInUserProfile$: Observable<UserProfile> | undefined;

  constructor(private httpClient: HttpClient) {}

  registerUser(userData: RegistrationRequest): Observable<any> {
    return this.httpClient
      .post(this.base_api + this.registerEndpoint, userData, {
        observe: 'response',
      })
      .pipe(
        map((response) => {
          if (response.status >= 200 && response.status < 300) {
            console.log('User registered successfully');
          }
          return response.body;
        }),
        catchError((error) => {
          if (error.status >= 400 && error.status < 500) {
            console.error('Bad request');
          } else if (error.status === 500) {
            console.error('Server error');
          }
          return throwError(() => error);
        })
      );
  }

  loginUser(userData: AuthenticationRequest): Observable<any> {
    return this.httpClient
      .post(this.base_api + this.loginEndpoint, userData, {
        responseType: 'text' as 'json',
        observe: 'response',
      })
      .pipe(
        map((response) => {
          if (response.status == 200) {
            console.log('User logged in successfully, from login service');
            console.log('Response: ', response);
            this.handleResponse(response);
          }
          return response.body;
        }),
        catchError((error) => {
          if (error.status >= 400 && error.status < 500) {
            console.error('Unauthorized');
          } else if (error.status === 500) {
            console.error('Server error');
          }
          return throwError(() => error);
        })
      );
  }

  handleResponse(response: any) {
    if (response) {
      localStorage.setItem('token', response.body);
      console.log('Token: ', response.body);
      this.token = response.body;
      this.userRole = this.getUserRole(this.token);
      this.getUserProfile();
    }
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserRole(token: string | null): any {
    if (token) {
      const decodedToken: any = jwtDecode(token);
      console.log('Decoded token: ', decodedToken);
      return decodedToken['role'];
    }
  }

  getUserProfile() {
    console.log('Getting user profile');
    console.log(
      'fetching user profile from URL: ',
      this.base_api + this.myProfileEndpoint
    );
    return (this.loggedInUserProfile$ = this.httpClient.get<UserProfile>(
      this.base_api + this.myProfileEndpoint,
      {
        headers: {
          Authorization: `Bearer ${this.getToken()}`,
        },
      }
    ));
  }
}
