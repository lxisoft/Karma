import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Reply } from 'app/shared/model/Karma/reply.model';
import { ReplyService } from './reply.service';
import { ReplyComponent } from './reply.component';
import { ReplyDetailComponent } from './reply-detail.component';
import { ReplyUpdateComponent } from './reply-update.component';
import { ReplyDeletePopupComponent } from './reply-delete-dialog.component';
import { IReply } from 'app/shared/model/Karma/reply.model';

@Injectable({ providedIn: 'root' })
export class ReplyResolve implements Resolve<IReply> {
  constructor(private service: ReplyService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Reply> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Reply>) => response.ok),
        map((reply: HttpResponse<Reply>) => reply.body)
      );
    }
    return of(new Reply());
  }
}

export const replyRoute: Routes = [
  {
    path: 'reply',
    component: ReplyComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaReply.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'reply/:id/view',
    component: ReplyDetailComponent,
    resolve: {
      reply: ReplyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaReply.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'reply/new',
    component: ReplyUpdateComponent,
    resolve: {
      reply: ReplyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaReply.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'reply/:id/edit',
    component: ReplyUpdateComponent,
    resolve: {
      reply: ReplyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaReply.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const replyPopupRoute: Routes = [
  {
    path: 'reply/:id/delete',
    component: ReplyDeletePopupComponent,
    resolve: {
      reply: ReplyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaReply.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
