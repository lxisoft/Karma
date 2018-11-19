import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFeed } from 'app/shared/model/Karma/feed.model';

@Component({
  selector: 'jhi-feed-detail',
  templateUrl: './feed-detail.component.html'
})
export class FeedDetailComponent implements OnInit {
  feed: IFeed;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ feed }) => {
      this.feed = feed;
    });
  }

  previousState() {
    window.history.back();
  }
}
