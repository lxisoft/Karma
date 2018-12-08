import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFeed } from 'app/shared/model/Karma/feed.model';

type EntityResponseType = HttpResponse<IFeed>;
type EntityArrayResponseType = HttpResponse<IFeed[]>;

@Injectable({ providedIn: 'root' })
export class FeedService {
  public resourceUrl = SERVER_API_URL + 'api/feeds';

  constructor(private http: HttpClient) {}

  create(feed: IFeed): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(feed);
    return this.http
      .post<IFeed>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(feed: IFeed): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(feed);
    return this.http
      .put<IFeed>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFeed>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFeed[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(feed: IFeed): IFeed {
    const copy: IFeed = Object.assign({}, feed, {
      date: feed.date != null && feed.date.isValid() ? feed.date.toJSON() : null
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
      res.body.forEach((feed: IFeed) => {
        feed.date = feed.date != null ? moment(feed.date) : null;
      });
    }
    return res;
  }
}
