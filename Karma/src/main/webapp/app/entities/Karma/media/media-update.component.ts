import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IMedia } from 'app/shared/model/Karma/media.model';
import { MediaService } from './media.service';
import { INeed } from 'app/shared/model/Karma/need.model';
import { NeedService } from 'app/entities/Karma/need';
import { IHelp } from 'app/shared/model/Karma/help.model';
import { HelpService } from 'app/entities/Karma/help';
import { IPost } from 'app/shared/model/Karma/post.model';
import { PostService } from 'app/entities/Karma/post';

@Component({
  selector: 'jhi-media-update',
  templateUrl: './media-update.component.html'
})
export class MediaUpdateComponent implements OnInit {
  media: IMedia;
  isSaving: boolean;

  needs: INeed[];

  helps: IHelp[];

  posts: IPost[];

  constructor(
    private dataUtils: JhiDataUtils,
    private jhiAlertService: JhiAlertService,
    private mediaService: MediaService,
    private needService: NeedService,
    private helpService: HelpService,
    private postService: PostService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ media }) => {
      this.media = media;
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
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, entity, field, isImage) {
    this.dataUtils.setFileData(event, entity, field, isImage);
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.media.id !== undefined) {
      this.subscribeToSaveResponse(this.mediaService.update(this.media));
    } else {
      this.subscribeToSaveResponse(this.mediaService.create(this.media));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IMedia>>) {
    result.subscribe((res: HttpResponse<IMedia>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
