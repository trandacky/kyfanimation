import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from '../../core/config/application-config.service';
@Injectable({
  providedIn: 'root',
})
export class FirmService {
  private urlGateway = this.applicationConfigService.getEndpointFor('api/admin');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getNotification(): Observable<any> {
    return this.http.get(this.urlGateway + `/notification`);
  }
  updateNotification(data: FormData): Observable<any> {
    return this.http.put(this.urlGateway + `/notification`, data);
  }
}
