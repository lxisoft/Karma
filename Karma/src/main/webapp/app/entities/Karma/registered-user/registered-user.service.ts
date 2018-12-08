import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';

type EntityResponseType = HttpResponse<IRegisteredUser>;
type EntityArrayResponseType = HttpResponse<IRegisteredUser[]>;

@Injectable({ providedIn: 'root' })
export class RegisteredUserService {
  public resourceUrl = SERVER_API_URL + 'api/registered-users';

  constructor(private http: HttpClient) {}

  create(registeredUser: IRegisteredUser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(registeredUser);
    return this.http
      .post<IRegisteredUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(registeredUser: IRegisteredUser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(registeredUser);
    return this.http
      .put<IRegisteredUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRegisteredUser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRegisteredUser[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(registeredUser: IRegisteredUser): IRegisteredUser {
    const copy: IRegisteredUser = Object.assign({}, registeredUser, {
      dob: registeredUser.dob != null && registeredUser.dob.isValid() ? registeredUser.dob.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dob = res.body.dob != null ? moment(res.body.dob) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((registeredUser: IRegisteredUser) => {
        registeredUser.dob = registeredUser.dob != null ? moment(registeredUser.dob) : null;
      });
    }
    return res;
  }
}
