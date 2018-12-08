import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IdentityProofType } from 'app/shared/model/Karma/identity-proof-type.model';
import { IdentityProofTypeService } from './identity-proof-type.service';
import { IdentityProofTypeComponent } from './identity-proof-type.component';
import { IdentityProofTypeDetailComponent } from './identity-proof-type-detail.component';
import { IdentityProofTypeUpdateComponent } from './identity-proof-type-update.component';
import { IdentityProofTypeDeletePopupComponent } from './identity-proof-type-delete-dialog.component';
import { IIdentityProofType } from 'app/shared/model/Karma/identity-proof-type.model';

@Injectable({ providedIn: 'root' })
export class IdentityProofTypeResolve implements Resolve<IIdentityProofType> {
  constructor(private service: IdentityProofTypeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IdentityProofType> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<IdentityProofType>) => response.ok),
        map((identityProofType: HttpResponse<IdentityProofType>) => identityProofType.body)
      );
    }
    return of(new IdentityProofType());
  }
}

export const identityProofTypeRoute: Routes = [
  {
    path: 'identity-proof-type',
    component: IdentityProofTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaIdentityProofType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'identity-proof-type/:id/view',
    component: IdentityProofTypeDetailComponent,
    resolve: {
      identityProofType: IdentityProofTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaIdentityProofType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'identity-proof-type/new',
    component: IdentityProofTypeUpdateComponent,
    resolve: {
      identityProofType: IdentityProofTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaIdentityProofType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'identity-proof-type/:id/edit',
    component: IdentityProofTypeUpdateComponent,
    resolve: {
      identityProofType: IdentityProofTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaIdentityProofType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const identityProofTypePopupRoute: Routes = [
  {
    path: 'identity-proof-type/:id/delete',
    component: IdentityProofTypeDeletePopupComponent,
    resolve: {
      identityProofType: IdentityProofTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaIdentityProofType.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
