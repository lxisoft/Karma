import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUserCheck } from 'app/shared/model/Karma/user-check.model';
import { UserCheckService } from './user-check.service';
import { INeed } from 'app/shared/model/Karma/need.model';
import { NeedService } from 'app/entities/Karma/need';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from 'app/entities/Karma/registered-user';
import { IComment } from 'app/shared/model/Karma/comment.model';
import { CommentService } from 'app/entities/Karma/comment';
import { IReply } from 'app/shared/model/Karma/reply.model';
import { ReplyService } from 'app/entities/Karma/reply';
import { IPost } from 'app/shared/model/Karma/post.model';
import { PostService } from 'app/entities/Karma/post';
import { IHelp } from 'app/shared/model/Karma/help.model';
import { HelpService } from 'app/entities/Karma/help';

@Component({
  selector: 'jhi-user-check-update',
  templateUrl: './user-check-update.component.html'
})
export class UserCheckUpdateComponent implements OnInit {
  userCheck: IUserCheck;
  isSaving: boolean;

  needs: INeed[];

  registeredusers: IRegisteredUser[];

  comments: IComment[];

  replies: IReply[];

  posts: IPost[];

  helps: IHelp[];

  constructor(
    private jhiAlertService: JhiAlertService,
    private userCheckService: UserCheckService,
    private needService: NeedService,
    private registeredUserService: RegisteredUserService,
    private commentService: CommentService,
    private replyService: ReplyService,
    private postService: PostService,
    private helpService: HelpService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ userCheck }) => {
      this.userCheck = userCheck;
    });
    this.needService.query().subscribe(
      (res: HttpResponse<INeed[]>) => {
        this.needs = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.registeredUserService.query().subscribe(
      (res: HttpResponse<IRegisteredUser[]>) => {
        this.registeredusers = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.commentService.query().subscribe(
      (res: HttpResponse<IComment[]>) => {
        this.comments = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.replyService.query().subscribe(
      (res: HttpResponse<IReply[]>) => {
        this.replies = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.postService.query().subscribe(
      (res: HttpResponse<IPost[]>) => {
        this.posts = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.helpService.query().subscribe(
      (res: HttpResponse<IHelp[]>) => {
        this.helps = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.userCheck.id !== undefined) {
      this.subscribeToSaveResponse(this.userCheckService.update(this.userCheck));
    } else {
      this.subscribeToSaveResponse(this.userCheckService.create(this.userCheck));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IUserCheck>>) {
    result.subscribe((res: HttpResponse<IUserCheck>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackRegisteredUserById(index: number, item: IRegisteredUser) {
    return item.id;
  }

  trackCommentById(index: number, item: IComment) {
    return item.id;
  }

  trackReplyById(index: number, item: IReply) {
    return item.id;
  }

  trackPostById(index: number, item: IPost) {
    return item.id;
  }

  trackHelpById(index: number, item: IHelp) {
    return item.id;
  }
}
