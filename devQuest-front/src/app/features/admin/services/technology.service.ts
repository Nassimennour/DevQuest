import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Technology } from '../models/admin-models';

@Injectable({
  providedIn: 'root',
})
export class TechnologyService {
  BASE_API = environment.apiUrl;
  getAllTechnologiesEndpoint = environment.endpoints.getAllTechnologiesAdmin;
  technologyList$!: Observable<Technology[]>;

  constructor(private http: HttpClient) {}

  getAllTechnologies(): Observable<Technology[]> {
    return (this.technologyList$ = this.http.get<Technology[]>(
      `${this.BASE_API}${this.getAllTechnologiesEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    ));
  }
}
