import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IIdentityProof } from 'app/shared/model/Karma/identity-proof.model';
import { IdentityProofService } from './identity-proof.service';
import { IIdentityProofType } from 'app/shared/model/Karma/identity-proof-type.model';
import { IdentityProofTypeService } from 'app/entities/Karma/identity-proof-type';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from 'app/entities/Karma/registered-user';

@Component({
  selector: 'jhi-identity-proof-update',
  templateUrl: './identity-proof-update.component.html'
})
export class IdentityProofUpdateComponent implements OnInit {
  identityProof: IIdentityProof;
  isSaving: boolean;

  identityprooftypes: IIdentityProofType[];

  registeredusers: IRegisteredUser[];

  constructor(
    private jhiAlertService: JhiAlertService,
    private identityProofService: IdentityProofService,
    private identityProofTypeService: IdentityProofTypeService,
    private registeredUserService: RegisteredUserService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ identityProof }) => {
      this.identityProof = identityProof;
    });
    this.identityProofTypeService.query().subscribe(
      (res: HttpResponse<IIdentityProofType[]>) => {
        this.identityprooftypes = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.registeredUserService.query().subscribe(
      (res: HttpResponse<IRegisteredUser[]>) => {
        this.registeredusers = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.identityProof.id !== undefined) {
      this.subscribeToSaveResponse(this.identityProofService.update(this.identityProof));
    } else {
      this.subscribeToSaveResponse(this.identityProofService.create(this.identityProof));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IIdentityProof>>) {
    result.subscribe((res: HttpResponse<IIdentityProof>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }

  private onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackIdentityProofTypeById(index: number, item: IIdentityProofType) {
    return item.id;
  }

  trackRegisteredUserById(index: number, item: IRegisteredUser) {
    return item.id;
  }
}
