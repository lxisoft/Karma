import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPost } from 'app/shared/model/Karma/post.model';

@Component({
  selector: 'jhi-post-detail',
  templateUrl: './post-detail.component.html'
})
export class PostDetailComponent implements OnInit {
  post: IPost;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ post }) => {
      this.post = post;
    });
  }

  previousState() {
    window.history.back();
  }
}
