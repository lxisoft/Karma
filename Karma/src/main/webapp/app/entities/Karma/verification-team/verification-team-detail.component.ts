import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVerificationTeam } from 'app/shared/model/Karma/verification-team.model';

@Component({
  selector: 'jhi-verification-team-detail',
  templateUrl: './verification-team-detail.component.html'
})
export class VerificationTeamDetailComponent implements OnInit {
  verificationTeam: IVerificationTeam;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ verificationTeam }) => {
      this.verificationTeam = verificationTeam;
    });
  }

  previousState() {
    window.history.back();
  }
}
