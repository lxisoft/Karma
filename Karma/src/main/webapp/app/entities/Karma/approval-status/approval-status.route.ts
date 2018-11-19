import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApprovalStatus } from 'app/shared/model/Karma/approval-status.model';
import { ApprovalStatusService } from './approval-status.service';
import { ApprovalStatusComponent } from './approval-status.component';
import { ApprovalStatusDetailComponent } from './approval-status-detail.component';
import { ApprovalStatusUpdateComponent } from './approval-status-update.component';
import { ApprovalStatusDeletePopupComponent } from './approval-status-delete-dialog.component';
import { IApprovalStatus } from 'app/shared/model/Karma/approval-status.model';

@Injectable({ providedIn: 'root' })
export class ApprovalStatusResolve implements Resolve<IApprovalStatus> {
  constructor(private service: ApprovalStatusService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ApprovalStatus> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ApprovalStatus>) => response.ok),
        map((approvalStatus: HttpResponse<ApprovalStatus>) => approvalStatus.body)
      );
    }
    return of(new ApprovalStatus());
  }
}

export const approvalStatusRoute: Routes = [
  {
    path: 'approval-status',
    component: ApprovalStatusComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaApprovalStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'approval-status/:id/view',
    component: ApprovalStatusDetailComponent,
    resolve: {
      approvalStatus: ApprovalStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaApprovalStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'approval-status/new',
    component: ApprovalStatusUpdateComponent,
    resolve: {
      approvalStatus: ApprovalStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaApprovalStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'approval-status/:id/edit',
    component: ApprovalStatusUpdateComponent,
    resolve: {
      approvalStatus: ApprovalStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaApprovalStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const approvalStatusPopupRoute: Routes = [
  {
    path: 'approval-status/:id/delete',
    component: ApprovalStatusDeletePopupComponent,
    resolve: {
      approvalStatus: ApprovalStatusResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaApprovalStatus.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
