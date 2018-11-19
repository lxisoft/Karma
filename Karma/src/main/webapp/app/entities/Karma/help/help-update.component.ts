import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IHelp } from 'app/shared/model/Karma/help.model';
import { HelpService } from './help.service';
import { IApprovalStatus } from 'app/shared/model/Karma/approval-status.model';
import { ApprovalStatusService } from 'app/entities/Karma/approval-status';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from 'app/entities/Karma/registered-user';
import { INeed } from 'app/shared/model/Karma/need.model';
import { NeedService } from 'app/entities/Karma/need';

@Component({
  selector: 'jhi-help-update',
  templateUrl: './help-update.component.html'
})
export class HelpUpdateComponent implements OnInit {
  help: IHelp;
  isSaving: boolean;

  approvalstatuses: IApprovalStatus[];

  registeredusers: IRegisteredUser[];

  needs: INeed[];
  time: string;

  constructor(
    private jhiAlertService: JhiAlertService,
    private helpService: HelpService,
    private approvalStatusService: ApprovalStatusService,
    private registeredUserService: RegisteredUserService,
    private needService: NeedService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ help }) => {
      this.help = help;
      this.time = this.help.time != null ? this.help.time.format(DATE_TIME_FORMAT) : null;
    });
    this.approvalStatusService.query().subscribe(
      (res: HttpResponse<IApprovalStatus[]>) => {
        this.approvalstatuses = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.registeredUserService.query().subscribe(
      (res: HttpResponse<IRegisteredUser[]>) => {
        this.registeredusers = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.needService.query().subscribe(
      (res: HttpResponse<INeed[]>) => {
        this.needs = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.help.time = this.time != null ? moment(this.time, DATE_TIME_FORMAT) : null;
    if (this.help.id !== undefined) {
      this.subscribeToSaveResponse(this.helpService.update(this.help));
    } else {
      this.subscribeToSaveResponse(this.helpService.create(this.help));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IHelp>>) {
    result.subscribe((res: HttpResponse<IHelp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackApprovalStatusById(index: number, item: IApprovalStatus) {
    return item.id;
  }

  trackRegisteredUserById(index: number, item: IRegisteredUser) {
    return item.id;
  }

  trackNeedById(index: number, item: INeed) {
    return item.id;
  }
}
