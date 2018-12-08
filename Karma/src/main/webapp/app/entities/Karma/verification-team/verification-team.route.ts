import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { VerificationTeam } from 'app/shared/model/Karma/verification-team.model';
import { VerificationTeamService } from './verification-team.service';
import { VerificationTeamComponent } from './verification-team.component';
import { VerificationTeamDetailComponent } from './verification-team-detail.component';
import { VerificationTeamUpdateComponent } from './verification-team-update.component';
import { VerificationTeamDeletePopupComponent } from './verification-team-delete-dialog.component';
import { IVerificationTeam } from 'app/shared/model/Karma/verification-team.model';

@Injectable({ providedIn: 'root' })
export class VerificationTeamResolve implements Resolve<IVerificationTeam> {
  constructor(private service: VerificationTeamService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<VerificationTeam> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<VerificationTeam>) => response.ok),
        map((verificationTeam: HttpResponse<VerificationTeam>) => verificationTeam.body)
      );
    }
    return of(new VerificationTeam());
  }
}

export const verificationTeamRoute: Routes = [
  {
    path: 'verification-team',
    component: VerificationTeamComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaVerificationTeam.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'verification-team/:id/view',
    component: VerificationTeamDetailComponent,
    resolve: {
      verificationTeam: VerificationTeamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaVerificationTeam.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'verification-team/new',
    component: VerificationTeamUpdateComponent,
    resolve: {
      verificationTeam: VerificationTeamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaVerificationTeam.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'verification-team/:id/edit',
    component: VerificationTeamUpdateComponent,
    resolve: {
      verificationTeam: VerificationTeamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaVerificationTeam.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const verificationTeamPopupRoute: Routes = [
  {
    path: 'verification-team/:id/delete',
    component: VerificationTeamDeletePopupComponent,
    resolve: {
      verificationTeam: VerificationTeamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaVerificationTeam.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
