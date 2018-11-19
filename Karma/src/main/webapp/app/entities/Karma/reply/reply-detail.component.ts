import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReply } from 'app/shared/model/Karma/reply.model';

@Component({
  selector: 'jhi-reply-detail',
  templateUrl: './reply-detail.component.html'
})
export class ReplyDetailComponent implements OnInit {
  reply: IReply;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ reply }) => {
      this.reply = reply;
    });
  }

  previousState() {
    window.history.back();
  }
}
