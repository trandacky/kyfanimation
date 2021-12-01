import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from '../../core/config/application-config.service';
@Injectable({
  providedIn: 'root',
})
export class TheloaiService {
  private urlGateway = this.applicationConfigService.getEndpointFor('api/admin/category');

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  getAllCategory(): Observable<any> {
    return this.http.get(this.urlGateway);
  }

  getCategoryPaging(page : number): Observable<any> {
    return this.http.get(this.urlGateway + `/${page}`);
  }

  updateCategory(data: FormData): Observable<any> {
    return this.http.put(this.urlGateway + `/update`, data);
  }

  createCategory(data: FormData): Observable<any> {
    return this.http.put(this.urlGateway + `/new`, data);
  }
}
