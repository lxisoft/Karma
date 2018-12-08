import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedia } from 'app/shared/model/Karma/media.model';

@Component({
  selector: 'jhi-media-detail',
  templateUrl: './media-detail.component.html'
})
export class MediaDetailComponent implements OnInit {
  media: IMedia;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ media }) => {
      this.media = media;
    });
  }

  previousState() {
    window.history.back();
  }
}
