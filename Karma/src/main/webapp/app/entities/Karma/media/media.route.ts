import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Media } from 'app/shared/model/Karma/media.model';
import { MediaService } from './media.service';
import { MediaComponent } from './media.component';
import { MediaDetailComponent } from './media-detail.component';
import { MediaUpdateComponent } from './media-update.component';
import { MediaDeletePopupComponent } from './media-delete-dialog.component';
import { IMedia } from 'app/shared/model/Karma/media.model';

@Injectable({ providedIn: 'root' })
export class MediaResolve implements Resolve<IMedia> {
  constructor(private service: MediaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Media> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Media>) => response.ok),
        map((media: HttpResponse<Media>) => media.body)
      );
    }
    return of(new Media());
  }
}

export const mediaRoute: Routes = [
  {
    path: 'media',
    component: MediaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaMedia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'media/:id/view',
    component: MediaDetailComponent,
    resolve: {
      media: MediaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaMedia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'media/new',
    component: MediaUpdateComponent,
    resolve: {
      media: MediaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaMedia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'media/:id/edit',
    component: MediaUpdateComponent,
    resolve: {
      media: MediaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaMedia.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mediaPopupRoute: Routes = [
  {
    path: 'media/:id/delete',
    component: MediaDeletePopupComponent,
    resolve: {
      media: MediaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaMedia.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
