import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Publisher } from 'app/shared/model/publisher.model';
import { PublisherService } from './publisher.service';
import { PublisherComponent } from './publisher.component';
import { PublisherDetailComponent } from './publisher-detail.component';
import { PublisherUpdateComponent } from './publisher-update.component';
import { PublisherDeletePopupComponent } from './publisher-delete-dialog.component';
import { IPublisher } from 'app/shared/model/publisher.model';

@Injectable({ providedIn: 'root' })
export class PublisherResolve implements Resolve<IPublisher> {
    constructor(private service: PublisherService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((publisher: HttpResponse<Publisher>) => publisher.body));
        }
        return of(new Publisher());
    }
}

export const publisherRoute: Routes = [
    {
        path: 'publisher',
        component: PublisherComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'libraryApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'publisher/:id/view',
        component: PublisherDetailComponent,
        resolve: {
            publisher: PublisherResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'libraryApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'publisher/new',
        component: PublisherUpdateComponent,
        resolve: {
            publisher: PublisherResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'libraryApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'publisher/:id/edit',
        component: PublisherUpdateComponent,
        resolve: {
            publisher: PublisherResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'libraryApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const publisherPopupRoute: Routes = [
    {
        path: 'publisher/:id/delete',
        component: PublisherDeletePopupComponent,
        resolve: {
            publisher: PublisherResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'libraryApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
