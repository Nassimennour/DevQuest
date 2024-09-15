import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';
import {
  ChangePasswordDTO,
  Technology,
  UserProfile,
} from '../../../shared/models/usermodels';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  API_URL: string = environment.apiUrl;
  getMyProfileEndpoint: string = environment.userEndpoints.getMyProfile;
  getMyDashboardEndpoint: string = environment.userEndpoints.getUserDashboard;
  getMyRankingEndpoint: string = environment.userEndpoints.getUserRanking;
  updateMyProfileEndpoint: string = environment.userEndpoints.updateProfile;
  changePasswordEndpoint: string = environment.userEndpoints.changePassword;
  addSkillEndpoint: string = environment.userEndpoints.addSkill;
  getAllRankingsEndpoint: string = environment.userEndpoints.getAllRankingsUser;
  getAllTechnologiesEndpoint: string =
    environment.userEndpoints.getAllTechnologies;

  constructor(private http: HttpClient) {}

  getMyProfile(): Observable<UserProfile> {
    return this.http.get<UserProfile>(
      `${this.API_URL}${this.getMyProfileEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getMyDashboard() {
    return this.http.get(`${this.API_URL}${this.getMyDashboardEndpoint}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    });
  }

  getMyRanking() {
    return this.http.get(`${this.API_URL}${this.getMyRankingEndpoint}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    });
  }

  updateMyProfile(data: any): Observable<UserProfile> {
    return this.http.put<UserProfile>(
      `${this.API_URL}${this.updateMyProfileEndpoint}`,
      data,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getAllRankings() {
    return this.http.get(`${this.API_URL}${this.getAllRankingsEndpoint}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      },
    });
  }

  addSkill(technologyId: number) {
    return this.http.put(
      `${this.API_URL}${this.addSkillEndpoint}${technologyId}`,
      {},
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  getAllTechnologies(): Observable<Technology[]> {
    return this.http.get<Technology[]>(
      `${this.API_URL}${this.getAllTechnologiesEndpoint}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      }
    );
  }

  changePassword(data: ChangePasswordDTO) {
    return this.http.put(
      `${this.API_URL}${this.changePasswordEndpoint}`,
      data,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
        responseType: 'text',
      }
    );
  }
}
