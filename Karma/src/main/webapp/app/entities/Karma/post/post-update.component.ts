import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IPost } from 'app/shared/model/Karma/post.model';
import { PostService } from './post.service';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from 'app/entities/Karma/registered-user';

@Component({
  selector: 'jhi-post-update',
  templateUrl: './post-update.component.html'
})
export class PostUpdateComponent implements OnInit {
  post: IPost;
  isSaving: boolean;

  registeredusers: IRegisteredUser[];
  date: string;

  constructor(
    private jhiAlertService: JhiAlertService,
    private postService: PostService,
    private registeredUserService: RegisteredUserService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ post }) => {
      this.post = post;
      this.date = this.post.date != null ? this.post.date.format(DATE_TIME_FORMAT) : null;
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
    this.post.date = this.date != null ? moment(this.date, DATE_TIME_FORMAT) : null;
    if (this.post.id !== undefined) {
      this.subscribeToSaveResponse(this.postService.update(this.post));
    } else {
      this.subscribeToSaveResponse(this.postService.create(this.post));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IPost>>) {
    result.subscribe((res: HttpResponse<IPost>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
