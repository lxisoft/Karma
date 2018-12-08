import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIdentityProof } from 'app/shared/model/Karma/identity-proof.model';

@Component({
  selector: 'jhi-identity-proof-detail',
  templateUrl: './identity-proof-detail.component.html'
})
export class IdentityProofDetailComponent implements OnInit {
  identityProof: IIdentityProof;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ identityProof }) => {
      this.identityProof = identityProof;
    });
  }

  previousState() {
    window.history.back();
  }
}
