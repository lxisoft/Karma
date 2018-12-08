import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Need } from 'app/shared/model/Karma/need.model';
import { NeedService } from './need.service';
import { NeedComponent } from './need.component';
import { NeedDetailComponent } from './need-detail.component';
import { NeedUpdateComponent } from './need-update.component';
import { NeedDeletePopupComponent } from './need-delete-dialog.component';
import { INeed } from 'app/shared/model/Karma/need.model';

@Injectable({ providedIn: 'root' })
export class NeedResolve implements Resolve<INeed> {
  constructor(private service: NeedService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Need> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Need>) => response.ok),
        map((need: HttpResponse<Need>) => need.body)
      );
    }
    return of(new Need());
  }
}

export const needRoute: Routes = [
  {
    path: 'need',
    component: NeedComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaNeed.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'need/:id/view',
    component: NeedDetailComponent,
    resolve: {
      need: NeedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaNeed.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'need/new',
    component: NeedUpdateComponent,
    resolve: {
      need: NeedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaNeed.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'need/:id/edit',
    component: NeedUpdateComponent,
    resolve: {
      need: NeedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaNeed.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const needPopupRoute: Routes = [
  {
    path: 'need/:id/delete',
    component: NeedDeletePopupComponent,
    resolve: {
      need: NeedResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaNeed.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
