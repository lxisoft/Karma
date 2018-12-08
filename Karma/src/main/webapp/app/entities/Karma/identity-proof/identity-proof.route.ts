import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IdentityProof } from 'app/shared/model/Karma/identity-proof.model';
import { IdentityProofService } from './identity-proof.service';
import { IdentityProofComponent } from './identity-proof.component';
import { IdentityProofDetailComponent } from './identity-proof-detail.component';
import { IdentityProofUpdateComponent } from './identity-proof-update.component';
import { IdentityProofDeletePopupComponent } from './identity-proof-delete-dialog.component';
import { IIdentityProof } from 'app/shared/model/Karma/identity-proof.model';

@Injectable({ providedIn: 'root' })
export class IdentityProofResolve implements Resolve<IIdentityProof> {
  constructor(private service: IdentityProofService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IdentityProof> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<IdentityProof>) => response.ok),
        map((identityProof: HttpResponse<IdentityProof>) => identityProof.body)
      );
    }
    return of(new IdentityProof());
  }
}

export const identityProofRoute: Routes = [
  {
    path: 'identity-proof',
    component: IdentityProofComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaIdentityProof.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'identity-proof/:id/view',
    component: IdentityProofDetailComponent,
    resolve: {
      identityProof: IdentityProofResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaIdentityProof.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'identity-proof/new',
    component: IdentityProofUpdateComponent,
    resolve: {
      identityProof: IdentityProofResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaIdentityProof.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'identity-proof/:id/edit',
    component: IdentityProofUpdateComponent,
    resolve: {
      identityProof: IdentityProofResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaIdentityProof.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const identityProofPopupRoute: Routes = [
  {
    path: 'identity-proof/:id/delete',
    component: IdentityProofDeletePopupComponent,
    resolve: {
      identityProof: IdentityProofResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaIdentityProof.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
