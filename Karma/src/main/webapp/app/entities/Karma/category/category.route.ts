import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Category } from 'app/shared/model/Karma/category.model';
import { CategoryService } from './category.service';
import { CategoryComponent } from './category.component';
import { CategoryDetailComponent } from './category-detail.component';
import { CategoryUpdateComponent } from './category-update.component';
import { CategoryDeletePopupComponent } from './category-delete-dialog.component';
import { ICategory } from 'app/shared/model/Karma/category.model';

@Injectable({ providedIn: 'root' })
export class CategoryResolve implements Resolve<ICategory> {
  constructor(private service: CategoryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Category> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Category>) => response.ok),
        map((category: HttpResponse<Category>) => category.body)
      );
    }
    return of(new Category());
  }
}

export const categoryRoute: Routes = [
  {
    path: 'category',
    component: CategoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'karmaApp.karmaCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'category/:id/view',
    component: CategoryDetailComponent,
    resolve: {
      category: CategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'category/new',
    component: CategoryUpdateComponent,
    resolve: {
      category: CategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'category/:id/edit',
    component: CategoryUpdateComponent,
    resolve: {
      category: CategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaCategory.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const categoryPopupRoute: Routes = [
  {
    path: 'category/:id/delete',
    component: CategoryDeletePopupComponent,
    resolve: {
      category: CategoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'karmaApp.karmaCategory.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
