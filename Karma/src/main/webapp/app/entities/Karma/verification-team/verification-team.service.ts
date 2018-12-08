import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVerificationTeam } from 'app/shared/model/Karma/verification-team.model';

type EntityResponseType = HttpResponse<IVerificationTeam>;
type EntityArrayResponseType = HttpResponse<IVerificationTeam[]>;

@Injectable({ providedIn: 'root' })
export class VerificationTeamService {
  public resourceUrl = SERVER_API_URL + 'api/verification-teams';

  constructor(private http: HttpClient) {}

  create(verificationTeam: IVerificationTeam): Observable<EntityResponseType> {
    return this.http.post<IVerificationTeam>(this.resourceUrl, verificationTeam, { observe: 'response' });
  }

  update(verificationTeam: IVerificationTeam): Observable<EntityResponseType> {
    return this.http.put<IVerificationTeam>(this.resourceUrl, verificationTeam, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVerificationTeam>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVerificationTeam[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
