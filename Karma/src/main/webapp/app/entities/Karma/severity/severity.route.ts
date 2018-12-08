import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Severity } from 'app/shared/model/Karma/severity.model';
import { SeverityService } from './severity.service';
import { SeverityComponent } from './severity.component';
import { SeverityDetailComponent } from './severity-detail.component';
import { SeverityUpdateComponent } from './severity-update.component';
import { SeverityDeletePopupComponent } from './severity-delete-dialog.component';
import { ISeverity } from 'app/shared/model/Karma/severity.model';

@Injectable({ providedIn: 'root' })
export class SeverityResolve implements Resolve<ISeverity> {
  constructor(private service: SeverityService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Severity> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Severity>) => response.ok),
        map((severity: HttpResponse<Severity>) => severity.body)
      );
    }
    return of(new Severity());
  }
}

export const severityRoute: Routes = [
  {
    path: 'severity',
    component: SeverityComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaSeverity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'severity/:id/view',
    component: SeverityDetailComponent,
    resolve: {
      severity: SeverityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaSeverity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'severity/new',
    component: SeverityUpdateComponent,
    resolve: {
      severity: SeverityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaSeverity.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'severity/:id/edit',
    component: SeverityUpdateComponent,
    resolve: {
      severity: SeverityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaSeverity.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const severityPopupRoute: Routes = [
  {
    path: 'severity/:id/delete',
    component: SeverityDeletePopupComponent,
    resolve: {
      severity: SeverityResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaSeverity.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
