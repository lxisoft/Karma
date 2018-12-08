import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUserCheck } from 'app/shared/model/Karma/user-check.model';

type EntityResponseType = HttpResponse<IUserCheck>;
type EntityArrayResponseType = HttpResponse<IUserCheck[]>;

@Injectable({ providedIn: 'root' })
export class UserCheckService {
  public resourceUrl = SERVER_API_URL + 'api/user-checks';

  constructor(private http: HttpClient) {}

  create(userCheck: IUserCheck): Observable<EntityResponseType> {
    return this.http.post<IUserCheck>(this.resourceUrl, userCheck, { observe: 'response' });
  }

  update(userCheck: IUserCheck): Observable<EntityResponseType> {
    return this.http.put<IUserCheck>(this.resourceUrl, userCheck, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserCheck>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserCheck[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
