import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISeverity } from 'app/shared/model/Karma/severity.model';

type EntityResponseType = HttpResponse<ISeverity>;
type EntityArrayResponseType = HttpResponse<ISeverity[]>;

@Injectable({ providedIn: 'root' })
export class SeverityService {
  public resourceUrl = SERVER_API_URL + 'api/severities';

  constructor(private http: HttpClient) {}

  create(severity: ISeverity): Observable<EntityResponseType> {
    return this.http.post<ISeverity>(this.resourceUrl, severity, { observe: 'response' });
  }

  update(severity: ISeverity): Observable<EntityResponseType> {
    return this.http.put<ISeverity>(this.resourceUrl, severity, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISeverity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISeverity[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
