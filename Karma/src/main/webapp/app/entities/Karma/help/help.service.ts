import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHelp } from 'app/shared/model/Karma/help.model';

type EntityResponseType = HttpResponse<IHelp>;
type EntityArrayResponseType = HttpResponse<IHelp[]>;

@Injectable({ providedIn: 'root' })
export class HelpService {
  public resourceUrl = SERVER_API_URL + 'api/helps';

  constructor(private http: HttpClient) {}

  create(help: IHelp): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(help);
    return this.http
      .post<IHelp>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(help: IHelp): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(help);
    return this.http
      .put<IHelp>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHelp>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHelp[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(help: IHelp): IHelp {
    const copy: IHelp = Object.assign({}, help, {
      time: help.time != null && help.time.isValid() ? help.time.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.time = res.body.time != null ? moment(res.body.time) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((help: IHelp) => {
        help.time = help.time != null ? moment(help.time) : null;
      });
    }
    return res;
  }
}
