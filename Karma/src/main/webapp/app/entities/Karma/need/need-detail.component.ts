import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INeed } from 'app/shared/model/Karma/need.model';

@Component({
  selector: 'jhi-need-detail',
  templateUrl: './need-detail.component.html'
})
export class NeedDetailComponent implements OnInit {
  need: INeed;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ need }) => {
      this.need = need;
    });
  }

  previousState() {
    window.history.back();
  }
}
