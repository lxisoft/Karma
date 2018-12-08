import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IIdentityProof } from 'app/shared/model/Karma/identity-proof.model';

type EntityResponseType = HttpResponse<IIdentityProof>;
type EntityArrayResponseType = HttpResponse<IIdentityProof[]>;

@Injectable({ providedIn: 'root' })
export class IdentityProofService {
  public resourceUrl = SERVER_API_URL + 'api/identity-proofs';

  constructor(private http: HttpClient) {}

  create(identityProof: IIdentityProof): Observable<EntityResponseType> {
    return this.http.post<IIdentityProof>(this.resourceUrl, identityProof, { observe: 'response' });
  }

  update(identityProof: IIdentityProof): Observable<EntityResponseType> {
    return this.http.put<IIdentityProof>(this.resourceUrl, identityProof, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IIdentityProof>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIdentityProof[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
