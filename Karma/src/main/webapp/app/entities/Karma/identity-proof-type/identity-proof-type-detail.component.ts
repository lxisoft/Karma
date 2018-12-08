import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIdentityProofType } from 'app/shared/model/Karma/identity-proof-type.model';

@Component({
  selector: 'jhi-identity-proof-type-detail',
  templateUrl: './identity-proof-type-detail.component.html'
})
export class IdentityProofTypeDetailComponent implements OnInit {
  identityProofType: IIdentityProofType;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ identityProofType }) => {
      this.identityProofType = identityProofType;
    });
  }

  previousState() {
    window.history.back();
  }
}
