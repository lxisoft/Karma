import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Feed } from 'app/shared/model/Karma/feed.model';
import { FeedService } from './feed.service';
import { FeedComponent } from './feed.component';
import { FeedDetailComponent } from './feed-detail.component';
import { FeedUpdateComponent } from './feed-update.component';
import { FeedDeletePopupComponent } from './feed-delete-dialog.component';
import { IFeed } from 'app/shared/model/Karma/feed.model';

@Injectable({ providedIn: 'root' })
export class FeedResolve implements Resolve<IFeed> {
  constructor(private service: FeedService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Feed> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Feed>) => response.ok),
        map((feed: HttpResponse<Feed>) => feed.body)
      );
    }
    return of(new Feed());
  }
}

export const feedRoute: Routes = [
  {
    path: 'feed',
    component: FeedComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaFeed.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'feed/:id/view',
    component: FeedDetailComponent,
    resolve: {
      feed: FeedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaFeed.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'feed/new',
    component: FeedUpdateComponent,
    resolve: {
      feed: FeedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaFeed.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'feed/:id/edit',
    component: FeedUpdateComponent,
    resolve: {
      feed: FeedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaFeed.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const feedPopupRoute: Routes = [
  {
    path: 'feed/:id/delete',
    component: FeedDeletePopupComponent,
    resolve: {
      feed: FeedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaFeed.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
