import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserCheck } from 'app/shared/model/Karma/user-check.model';
import { UserCheckService } from './user-check.service';
import { UserCheckComponent } from './user-check.component';
import { UserCheckDetailComponent } from './user-check-detail.component';
import { UserCheckUpdateComponent } from './user-check-update.component';
import { UserCheckDeletePopupComponent } from './user-check-delete-dialog.component';
import { IUserCheck } from 'app/shared/model/Karma/user-check.model';

@Injectable({ providedIn: 'root' })
export class UserCheckResolve implements Resolve<IUserCheck> {
  constructor(private service: UserCheckService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<UserCheck> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<UserCheck>) => response.ok),
        map((userCheck: HttpResponse<UserCheck>) => userCheck.body)
      );
    }
    return of(new UserCheck());
  }
}

export const userCheckRoute: Routes = [
  {
    path: 'user-check',
    component: UserCheckComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaUserCheck.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'user-check/:id/view',
    component: UserCheckDetailComponent,
    resolve: {
      userCheck: UserCheckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaUserCheck.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'user-check/new',
    component: UserCheckUpdateComponent,
    resolve: {
      userCheck: UserCheckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaUserCheck.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'user-check/:id/edit',
    component: UserCheckUpdateComponent,
    resolve: {
      userCheck: UserCheckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaUserCheck.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const userCheckPopupRoute: Routes = [
  {
    path: 'user-check/:id/delete',
    component: UserCheckDeletePopupComponent,
    resolve: {
      userCheck: UserCheckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaUserCheck.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
