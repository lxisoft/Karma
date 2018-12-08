import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IVerificationTeam } from 'app/shared/model/Karma/verification-team.model';
import { VerificationTeamService } from './verification-team.service';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from 'app/entities/Karma/registered-user';

@Component({
  selector: 'jhi-verification-team-update',
  templateUrl: './verification-team-update.component.html'
})
export class VerificationTeamUpdateComponent implements OnInit {
  verificationTeam: IVerificationTeam;
  isSaving: boolean;

  registeredusers: IRegisteredUser[];

  constructor(
    private jhiAlertService: JhiAlertService,
    private verificationTeamService: VerificationTeamService,
    private registeredUserService: RegisteredUserService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ verificationTeam }) => {
      this.verificationTeam = verificationTeam;
    });
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
    if (this.verificationTeam.id !== undefined) {
      this.subscribeToSaveResponse(this.verificationTeamService.update(this.verificationTeam));
    } else {
      this.subscribeToSaveResponse(this.verificationTeamService.create(this.verificationTeam));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IVerificationTeam>>) {
    result.subscribe((res: HttpResponse<IVerificationTeam>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackRegisteredUserById(index: number, item: IRegisteredUser) {
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
