import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IComment } from 'app/shared/model/Karma/comment.model';
import { CommentService } from './comment.service';
import { INeed } from 'app/shared/model/Karma/need.model';
import { NeedService } from 'app/entities/Karma/need';
import { IHelp } from 'app/shared/model/Karma/help.model';
import { HelpService } from 'app/entities/Karma/help';
import { IPost } from 'app/shared/model/Karma/post.model';
import { PostService } from 'app/entities/Karma/post';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from 'app/entities/Karma/registered-user';

@Component({
  selector: 'jhi-comment-update',
  templateUrl: './comment-update.component.html'
})
export class CommentUpdateComponent implements OnInit {
  comment: IComment;
  isSaving: boolean;

  needs: INeed[];

  helps: IHelp[];

  posts: IPost[];

  registeredusers: IRegisteredUser[];
  date: string;

  constructor(
    private jhiAlertService: JhiAlertService,
    private commentService: CommentService,
    private needService: NeedService,
    private helpService: HelpService,
    private postService: PostService,
    private registeredUserService: RegisteredUserService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ comment }) => {
      this.comment = comment;
      this.date = this.comment.date != null ? this.comment.date.format(DATE_TIME_FORMAT) : null;
    });
    this.needService.query().subscribe(
      (res: HttpResponse<INeed[]>) => {
        this.needs = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.helpService.query().subscribe(
      (res: HttpResponse<IHelp[]>) => {
        this.helps = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.postService.query().subscribe(
      (res: HttpResponse<IPost[]>) => {
        this.posts = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
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
    this.comment.date = this.date != null ? moment(this.date, DATE_TIME_FORMAT) : null;
    if (this.comment.id !== undefined) {
      this.subscribeToSaveResponse(this.commentService.update(this.comment));
    } else {
      this.subscribeToSaveResponse(this.commentService.create(this.comment));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IComment>>) {
    result.subscribe((res: HttpResponse<IComment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackNeedById(index: number, item: INeed) {
    return item.id;
  }

  trackHelpById(index: number, item: IHelp) {
    return item.id;
  }

  trackPostById(index: number, item: IPost) {
    return item.id;
  }

  trackRegisteredUserById(index: number, item: IRegisteredUser) {
    return item.id;
  }
}
