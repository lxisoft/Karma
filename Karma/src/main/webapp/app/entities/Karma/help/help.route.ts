import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Help } from 'app/shared/model/Karma/help.model';
import { HelpService } from './help.service';
import { HelpComponent } from './help.component';
import { HelpDetailComponent } from './help-detail.component';
import { HelpUpdateComponent } from './help-update.component';
import { HelpDeletePopupComponent } from './help-delete-dialog.component';
import { IHelp } from 'app/shared/model/Karma/help.model';

@Injectable({ providedIn: 'root' })
export class HelpResolve implements Resolve<IHelp> {
  constructor(private service: HelpService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Help> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Help>) => response.ok),
        map((help: HttpResponse<Help>) => help.body)
      );
    }
    return of(new Help());
  }
}

export const helpRoute: Routes = [
  {
    path: 'help',
    component: HelpComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaHelp.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'help/:id/view',
    component: HelpDetailComponent,
    resolve: {
      help: HelpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaHelp.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'help/new',
    component: HelpUpdateComponent,
    resolve: {
      help: HelpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaHelp.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'help/:id/edit',
    component: HelpUpdateComponent,
    resolve: {
      help: HelpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaHelp.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const helpPopupRoute: Routes = [
  {
    path: 'help/:id/delete',
    component: HelpDeletePopupComponent,
    resolve: {
      help: HelpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaHelp.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
