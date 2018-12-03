import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from './registered-user.service';
import { IMedia } from 'app/shared/model/Karma/media.model';
import { MediaService } from 'app/entities/Karma/media';
import { IIdentityProof } from 'app/shared/model/Karma/identity-proof.model';
import { IdentityProofService } from 'app/entities/Karma/identity-proof';
import { IVerificationTeam } from 'app/shared/model/Karma/verification-team.model';
import { VerificationTeamService } from 'app/entities/Karma/verification-team';

@Component({
  selector: 'jhi-registered-user-update',
  templateUrl: './registered-user-update.component.html'
})
export class RegisteredUserUpdateComponent implements OnInit {
  registeredUser: IRegisteredUser;
  isSaving: boolean;

  profilepics: IMedia[];

  idproofs: IIdentityProof[];

  verificationteams: IVerificationTeam[];
  dobDp: any;

  constructor(
    private jhiAlertService: JhiAlertService,
    private registeredUserService: RegisteredUserService,
    private mediaService: MediaService,
    private identityProofService: IdentityProofService,
    private verificationTeamService: VerificationTeamService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ registeredUser }) => {
      this.registeredUser = registeredUser;
    });
    this.mediaService.query({ filter: 'registereduser-is-null' }).subscribe(
      (res: HttpResponse<IMedia[]>) => {
        if (!this.registeredUser.profilePicId) {
          this.profilepics = res.body;
        } else {
          this.mediaService.find(this.registeredUser.profilePicId).subscribe(
            (subRes: HttpResponse<IMedia>) => {
              this.profilepics = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.identityProofService.query({ filter: 'owner-is-null' }).subscribe(
      (res: HttpResponse<IIdentityProof[]>) => {
        if (!this.registeredUser.idProofId) {
          this.idproofs = res.body;
        } else {
          this.identityProofService.find(this.registeredUser.idProofId).subscribe(
            (subRes: HttpResponse<IIdentityProof>) => {
              this.idproofs = [subRes.body].concat(res.body);
            },
            (subRes: HttpErrorResponse) => this.onError(subRes.message)
          );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.verificationTeamService.query().subscribe(
      (res: HttpResponse<IVerificationTeam[]>) => {
        this.verificationteams = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.registeredUser.id !== undefined) {
      this.subscribeToSaveResponse(this.registeredUserService.update(this.registeredUser));
    } else {
      this.subscribeToSaveResponse(this.registeredUserService.create(this.registeredUser));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IRegisteredUser>>) {
    result.subscribe((res: HttpResponse<IRegisteredUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackMediaById(index: number, item: IMedia) {
    return item.id;
  }

  trackIdentityProofById(index: number, item: IIdentityProof) {
    return item.id;
  }

  trackVerificationTeamById(index: number, item: IVerificationTeam) {
    return item.id;
  }

  getSelected(selectedVals: Array<any>, option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
