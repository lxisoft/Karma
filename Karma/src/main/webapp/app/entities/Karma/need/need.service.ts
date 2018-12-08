import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INeed } from 'app/shared/model/Karma/need.model';

type EntityResponseType = HttpResponse<INeed>;
type EntityArrayResponseType = HttpResponse<INeed[]>;

@Injectable({ providedIn: 'root' })
export class NeedService {
  public resourceUrl = SERVER_API_URL + 'api/needs';

  constructor(private http: HttpClient) {}

  create(need: INeed): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(need);
    return this.http
      .post<INeed>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(need: INeed): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(need);
    return this.http
      .put<INeed>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INeed>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INeed[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(need: INeed): INeed {
    const copy: INeed = Object.assign({}, need, {
      date: need.date != null && need.date.isValid() ? need.date.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date != null ? moment(res.body.date) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((need: INeed) => {
        need.date = need.date != null ? moment(need.date) : null;
      });
    }
    return res;
  }
}
