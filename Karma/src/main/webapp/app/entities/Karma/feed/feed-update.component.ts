import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IFeed } from 'app/shared/model/Karma/feed.model';
import { FeedService } from './feed.service';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from 'app/entities/Karma/registered-user';

@Component({
  selector: 'jhi-feed-update',
  templateUrl: './feed-update.component.html'
})
export class FeedUpdateComponent implements OnInit {
  feed: IFeed;
  isSaving: boolean;

  registeredusers: IRegisteredUser[];
  date: string;

  constructor(
    private jhiAlertService: JhiAlertService,
    private feedService: FeedService,
    private registeredUserService: RegisteredUserService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ feed }) => {
      this.feed = feed;
      this.date = this.feed.date != null ? this.feed.date.format(DATE_TIME_FORMAT) : null;
    });
    this.registeredUserService.query().subscribe(
      (res: HttpResponse<IRegisteredUser[]>) => {
        this.registeredusers = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.feed.date = this.date != null ? moment(this.date, DATE_TIME_FORMAT) : null;
    if (this.feed.id !== undefined) {
      this.subscribeToSaveResponse(this.feedService.update(this.feed));
    } else {
      this.subscribeToSaveResponse(this.feedService.create(this.feed));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IFeed>>) {
    result.subscribe((res: HttpResponse<IFeed>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }

  private onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackRegisteredUserById(index: number, item: IRegisteredUser) {
    return item.id;
  }
}
