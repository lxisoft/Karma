import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from './registered-user.service';
import { RegisteredUserComponent } from './registered-user.component';
import { RegisteredUserDetailComponent } from './registered-user-detail.component';
import { RegisteredUserUpdateComponent } from './registered-user-update.component';
import { RegisteredUserDeletePopupComponent } from './registered-user-delete-dialog.component';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';

@Injectable({ providedIn: 'root' })
export class RegisteredUserResolve implements Resolve<IRegisteredUser> {
  constructor(private service: RegisteredUserService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RegisteredUser> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<RegisteredUser>) => response.ok),
        map((registeredUser: HttpResponse<RegisteredUser>) => registeredUser.body)
      );
    }
    return of(new RegisteredUser());
  }
}

export const registeredUserRoute: Routes = [
  {
    path: 'registered-user',
    component: RegisteredUserComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaRegisteredUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'registered-user/:id/view',
    component: RegisteredUserDetailComponent,
    resolve: {
      registeredUser: RegisteredUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaRegisteredUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'registered-user/new',
    component: RegisteredUserUpdateComponent,
    resolve: {
      registeredUser: RegisteredUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaRegisteredUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'registered-user/:id/edit',
    component: RegisteredUserUpdateComponent,
    resolve: {
      registeredUser: RegisteredUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaRegisteredUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const registeredUserPopupRoute: Routes = [
  {
    path: 'registered-user/:id/delete',
    component: RegisteredUserDeletePopupComponent,
    resolve: {
      registeredUser: RegisteredUserResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaRegisteredUser.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
