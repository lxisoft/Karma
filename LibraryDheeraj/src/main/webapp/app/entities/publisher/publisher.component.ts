import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPublisher } from 'app/shared/model/publisher.model';
import { Principal } from 'app/core';
import { PublisherService } from './publisher.service';

@Component({
    selector: 'jhi-publisher',
    templateUrl: './publisher.component.html'
})
export class PublisherComponent implements OnInit, OnDestroy {
    publishers: IPublisher[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private publisherService: PublisherService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.publisherService.query().subscribe(
            (res: HttpResponse<IPublisher[]>) => {
                this.publishers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPublishers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPublisher) {
        return item.id;
    }

    registerChangeInPublishers() {
        this.eventSubscriber = this.eventManager.subscribe('publisherListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
