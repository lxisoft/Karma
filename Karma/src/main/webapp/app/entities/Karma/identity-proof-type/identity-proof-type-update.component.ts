import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IIdentityProofType } from 'app/shared/model/Karma/identity-proof-type.model';
import { IdentityProofTypeService } from './identity-proof-type.service';

@Component({
  selector: 'jhi-identity-proof-type-update',
  templateUrl: './identity-proof-type-update.component.html'
})
export class IdentityProofTypeUpdateComponent implements OnInit {
  identityProofType: IIdentityProofType;
  isSaving: boolean;

  constructor(private identityProofTypeService: IdentityProofTypeService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ identityProofType }) => {
      this.identityProofType = identityProofType;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.identityProofType.id !== undefined) {
      this.subscribeToSaveResponse(this.identityProofTypeService.update(this.identityProofType));
    } else {
      this.subscribeToSaveResponse(this.identityProofTypeService.create(this.identityProofType));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IIdentityProofType>>) {
    result.subscribe((res: HttpResponse<IIdentityProofType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }
}
