import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IMedia } from 'app/shared/model/Karma/media.model';

@Component({
  selector: 'jhi-media-detail',
  templateUrl: './media-detail.component.html'
})
export class MediaDetailComponent implements OnInit {
  media: IMedia;

  constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ media }) => {
      this.media = media;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
