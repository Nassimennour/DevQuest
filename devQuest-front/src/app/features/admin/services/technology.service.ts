import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category, CategoryDTO, Technology } from '../models/admin-models';

@Injectable({
  providedIn: 'root',
})
export class TechnologyService {
  BASE_API = environment.apiUrl;
  getAllTechnologiesEndpoint = environment.endpoints.getAllTechnologiesAdmin;
  getTechnologyByIdEndpoint = environment.endpoints.getTechnologyByIdAdmin;
  createTechnologyEndpoint = environment.endpoints.createTechnologyAdmin;
  updateTechnologyEndpoint = environment.endpoints.updateTechnologyAdmin;
  deleteTechnologyEndpoint = environment.endpoints.deleteTechnologyAdmin;
  getAllCategoriesEndpoint = environment.endpoints.getAllCategoriesAdmin;
  createCategoryEndpoint = environment.endpoints.createCategoryAdmin;
  updateCategoryEndpoint = environment.endpoints.updateCategoryAdmin;
  deleteCategoryEndpoint = environment.endpoints.deleteCategoryAdmin;
  getCategoryByIdEndpoint = environment.endpoints.getCategoryByIdAdmin;
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

  getTechnologyById(id: number): Observable<Technology> {
    return this.http.get<Technology>(
      `${this.BASE_API}${this.getTechnologyByIdEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  createTechnology(technology: any): Observable<Technology> {
    return this.http.post<Technology>(
      `${this.BASE_API}${this.createTechnologyEndpoint}`,
      technology,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  updateTechnology(technology: Technology): Observable<Technology> {
    return this.http.put<Technology>(
      `${this.BASE_API}${this.updateTechnologyEndpoint}`,
      technology,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  deleteTechnology(id: number): Observable<void> {
    return this.http.delete<void>(
      `${this.BASE_API}${this.deleteTechnologyEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
        responseType: 'text' as 'json',
      }
    );
  }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(
      `${this.BASE_API}${this.getAllCategoriesEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  createCategory(category: CategoryDTO): Observable<Category> {
    return this.http.post<Category>(
      `${this.BASE_API}${this.createCategoryEndpoint}`,
      category,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  updateCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(
      `${this.BASE_API}${this.updateCategoryEndpoint}`,
      category,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(
      `${this.BASE_API}${this.deleteCategoryEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
        responseType: 'text' as 'json',
      }
    );
  }
}
