import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMedia } from 'app/shared/model/Karma/media.model';

type EntityResponseType = HttpResponse<IMedia>;
type EntityArrayResponseType = HttpResponse<IMedia[]>;

@Injectable({ providedIn: 'root' })
export class MediaService {
  public resourceUrl = SERVER_API_URL + 'api/media';

  constructor(private http: HttpClient) {}

  create(media: IMedia): Observable<EntityResponseType> {
    return this.http.post<IMedia>(this.resourceUrl, media, { observe: 'response' });
  }

  update(media: IMedia): Observable<EntityResponseType> {
    return this.http.put<IMedia>(this.resourceUrl, media, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedia>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedia[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
