import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import { User } from '../models/admin-models';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  BASE_API = environment.apiUrl;
  getAllUsersEndpoint = environment.endpoints.getAllUsersAdmin;
  deleteUsersEndpoint = environment.endpoints.deleteUserAdmin;
  updateUsersEndpoint = environment.endpoints.updateUserAdmin;
  getUserByIdEndpoint = environment.endpoints.getUserByIdAdmin;
  createUserEndpoint = environment.endpoints.createUserAdmin;

  usersList$: Observable<User[]> = new Observable<User[]>();

  constructor(private httpClient: HttpClient) {}

  getAllUsers(): Observable<User[]> {
    return (this.usersList$ = this.httpClient.get<User[]>(
      `${this.BASE_API}${this.getAllUsersEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    ));
  }

  deleteUser(id: number): Observable<any> {
    return this.httpClient.delete(
      `${this.BASE_API}${this.deleteUsersEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
        responseType: 'text',
      }
    );
  }

  updateUser(user: User): Observable<User> {
    // convert date to iso string
    user.birthDate = new Date(user.birthDate).toISOString();
    return this.httpClient.put<User>(
      this.BASE_API + this.updateUsersEndpoint,
      user,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(
      `${this.BASE_API}${this.getUserByIdEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  createUser(user: any): Observable<User> {
    // convert date to iso string
    user.birthDate = new Date(user.birthDate).toISOString();
    console.log('User to create : ', user);
    return this.httpClient.post<User>(
      this.BASE_API + this.createUserEndpoint,
      user,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }
}
