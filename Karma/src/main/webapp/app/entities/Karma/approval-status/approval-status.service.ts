import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApprovalStatus } from 'app/shared/model/Karma/approval-status.model';

type EntityResponseType = HttpResponse<IApprovalStatus>;
type EntityArrayResponseType = HttpResponse<IApprovalStatus[]>;

@Injectable({ providedIn: 'root' })
export class ApprovalStatusService {
  public resourceUrl = SERVER_API_URL + 'api/approval-statuses';

  constructor(private http: HttpClient) {}

  create(approvalStatus: IApprovalStatus): Observable<EntityResponseType> {
    return this.http.post<IApprovalStatus>(this.resourceUrl, approvalStatus, { observe: 'response' });
  }

  update(approvalStatus: IApprovalStatus): Observable<EntityResponseType> {
    return this.http.put<IApprovalStatus>(this.resourceUrl, approvalStatus, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IApprovalStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApprovalStatus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
