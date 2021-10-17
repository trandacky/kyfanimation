import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from '../core/config/application-config.service';
@Injectable({
  providedIn: 'root',
})
export class HomeService {
  private baseUrl = this.applicationConfigService.getEndpointFor('api/public');

  private userUrl = this.applicationConfigService.getEndpointFor('api/user');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getPublicNotification(): Observable<any> {
    return this.http.get(this.baseUrl + `/notification`);
  }
  getNotification(): Observable<any> {
    return this.http.get(this.userUrl + `/notification`);
  }
  updateUserProfile(data: FormData): Observable<any> {
    return this.http.put(this.baseUrl + `/notification`, data);
  }
}
