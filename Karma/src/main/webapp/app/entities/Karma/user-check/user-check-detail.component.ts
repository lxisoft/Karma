import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserCheck } from 'app/shared/model/Karma/user-check.model';

@Component({
  selector: 'jhi-user-check-detail',
  templateUrl: './user-check-detail.component.html'
})
export class UserCheckDetailComponent implements OnInit {
  userCheck: IUserCheck;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userCheck }) => {
      this.userCheck = userCheck;
    });
  }

  previousState() {
    window.history.back();
  }
}
