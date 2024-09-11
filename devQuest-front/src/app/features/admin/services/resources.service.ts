import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import { Resource, ResourceDTO } from '../models/admin-models';

@Injectable({
  providedIn: 'root',
})
export class ResourcesService {
  BASE_API = environment.apiUrl;
  getResourcesEndpoint = environment.endpoints.getResourcesByTechnologyIdAdmin;
  getResourceByIdEndpoint = environment.endpoints.getResourceByIdAdmin;
  createResourceEndpoint = environment.endpoints.createResourceAdmin;
  updateResourceEndpoint = environment.endpoints.updateResourceAdmin;
  deleteResourceEndpoint = environment.endpoints.deleteResourceAdmin;
  getResourcesByTechnologyIdEndpoint =
    environment.endpoints.getResourcesByTechnologyIdAdmin;

  constructor(private http: HttpClient) {}

  getResourcesByTechnologyId(technologyId: number): Observable<Resource[]> {
    return this.http.get<Resource[]>(
      `${this.BASE_API}${this.getResourcesByTechnologyIdEndpoint}${technologyId}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getAllResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(
      `${this.BASE_API}${this.getResourcesEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getResourceById(id: number): Observable<Resource> {
    return this.http.get<Resource>(
      `${this.BASE_API}${this.getResourceByIdEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  createResource(resource: ResourceDTO): Observable<Resource> {
    return this.http.post<Resource>(
      `${this.BASE_API}${this.createResourceEndpoint}`,
      resource,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  updateResource(resource: ResourceDTO): Observable<Resource> {
    return this.http.put<Resource>(
      `${this.BASE_API}${this.updateResourceEndpoint}`,
      resource,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  deleteResource(id: number): Observable<void> {
    return this.http.delete<void>(
      `${this.BASE_API}${this.deleteResourceEndpoint}${id}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }
}
