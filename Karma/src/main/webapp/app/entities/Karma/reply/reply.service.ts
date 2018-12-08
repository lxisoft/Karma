import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReply } from 'app/shared/model/Karma/reply.model';

type EntityResponseType = HttpResponse<IReply>;
type EntityArrayResponseType = HttpResponse<IReply[]>;

@Injectable({ providedIn: 'root' })
export class ReplyService {
  public resourceUrl = SERVER_API_URL + 'api/replies';

  constructor(private http: HttpClient) {}

  create(reply: IReply): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reply);
    return this.http
      .post<IReply>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(reply: IReply): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reply);
    return this.http
      .put<IReply>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IReply>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IReply[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(reply: IReply): IReply {
    const copy: IReply = Object.assign({}, reply, {
      date: reply.date != null && reply.date.isValid() ? reply.date.toJSON() : null
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
      res.body.forEach((reply: IReply) => {
        reply.date = reply.date != null ? moment(reply.date) : null;
      });
    }
    return res;
  }
}
