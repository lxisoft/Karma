import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IReply } from 'app/shared/model/Karma/reply.model';
import { ReplyService } from './reply.service';
import { IComment } from 'app/shared/model/Karma/comment.model';
import { CommentService } from 'app/entities/Karma/comment';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from 'app/entities/Karma/registered-user';

@Component({
  selector: 'jhi-reply-update',
  templateUrl: './reply-update.component.html'
})
export class ReplyUpdateComponent implements OnInit {
  reply: IReply;
  isSaving: boolean;

  comments: IComment[];

  registeredusers: IRegisteredUser[];
  date: string;

  constructor(
    private jhiAlertService: JhiAlertService,
    private replyService: ReplyService,
    private commentService: CommentService,
    private registeredUserService: RegisteredUserService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ reply }) => {
      this.reply = reply;
      this.date = this.reply.date != null ? this.reply.date.format(DATE_TIME_FORMAT) : null;
    });
    this.commentService.query().subscribe(
      (res: HttpResponse<IComment[]>) => {
        this.comments = res.body;
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
    this.reply.date = this.date != null ? moment(this.date, DATE_TIME_FORMAT) : null;
    if (this.reply.id !== undefined) {
      this.subscribeToSaveResponse(this.replyService.update(this.reply));
    } else {
      this.subscribeToSaveResponse(this.replyService.create(this.reply));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IReply>>) {
    result.subscribe((res: HttpResponse<IReply>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackCommentById(index: number, item: IComment) {
    return item.id;
  }

  trackRegisteredUserById(index: number, item: IRegisteredUser) {
    return item.id;
  }
}
