import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from '../core/config/application-config.service';
@Injectable({
  providedIn: 'root',
})
export class UserService {
  private urlGateway = this.applicationConfigService.getEndpointFor('api/user');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getUserProfile(): Observable<any> {
    return this.http.get(this.urlGateway + `/profile`);
  }
  updateUserProfile(data: FormData): Observable<any> {
    return this.http.put(this.urlGateway + `/profile`, data);
  }
}
