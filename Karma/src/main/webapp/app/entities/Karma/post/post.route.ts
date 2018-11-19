import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Post } from 'app/shared/model/Karma/post.model';
import { PostService } from './post.service';
import { PostComponent } from './post.component';
import { PostDetailComponent } from './post-detail.component';
import { PostUpdateComponent } from './post-update.component';
import { PostDeletePopupComponent } from './post-delete-dialog.component';
import { IPost } from 'app/shared/model/Karma/post.model';

@Injectable({ providedIn: 'root' })
export class PostResolve implements Resolve<IPost> {
  constructor(private service: PostService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Post> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Post>) => response.ok),
        map((post: HttpResponse<Post>) => post.body)
      );
    }
    return of(new Post());
  }
}

export const postRoute: Routes = [
  {
    path: 'post',
    component: PostComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaPost.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'post/:id/view',
    component: PostDetailComponent,
    resolve: {
      post: PostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaPost.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'post/new',
    component: PostUpdateComponent,
    resolve: {
      post: PostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaPost.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'post/:id/edit',
    component: PostUpdateComponent,
    resolve: {
      post: PostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaPost.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const postPopupRoute: Routes = [
  {
    path: 'post/:id/delete',
    component: PostDeletePopupComponent,
    resolve: {
      post: PostResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaPost.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
