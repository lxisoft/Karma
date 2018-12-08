import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IIdentityProofType } from 'app/shared/model/Karma/identity-proof-type.model';

type EntityResponseType = HttpResponse<IIdentityProofType>;
type EntityArrayResponseType = HttpResponse<IIdentityProofType[]>;

@Injectable({ providedIn: 'root' })
export class IdentityProofTypeService {
  public resourceUrl = SERVER_API_URL + 'api/identity-proof-types';

  constructor(private http: HttpClient) {}

  create(identityProofType: IIdentityProofType): Observable<EntityResponseType> {
    return this.http.post<IIdentityProofType>(this.resourceUrl, identityProofType, { observe: 'response' });
  }

  update(identityProofType: IIdentityProofType): Observable<EntityResponseType> {
    return this.http.put<IIdentityProofType>(this.resourceUrl, identityProofType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IIdentityProofType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIdentityProofType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
