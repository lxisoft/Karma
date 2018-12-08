import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';

@Component({
  selector: 'jhi-registered-user-detail',
  templateUrl: './registered-user-detail.component.html'
})
export class RegisteredUserDetailComponent implements OnInit {
  registeredUser: IRegisteredUser;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ registeredUser }) => {
      this.registeredUser = registeredUser;
    });
  }

  previousState() {
    window.history.back();
  }
}
