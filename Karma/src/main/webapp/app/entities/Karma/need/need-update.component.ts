import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { INeed } from 'app/shared/model/Karma/need.model';
import { NeedService } from './need.service';
import { ISeverity } from 'app/shared/model/Karma/severity.model';
import { SeverityService } from 'app/entities/Karma/severity';
import { IVerificationTeam } from 'app/shared/model/Karma/verification-team.model';
import { VerificationTeamService } from 'app/entities/Karma/verification-team';
import { IApprovalStatus } from 'app/shared/model/Karma/approval-status.model';
import { ApprovalStatusService } from 'app/entities/Karma/approval-status';
import { IRegisteredUser } from 'app/shared/model/Karma/registered-user.model';
import { RegisteredUserService } from 'app/entities/Karma/registered-user';
import { ICategory } from 'app/shared/model/Karma/category.model';
import { CategoryService } from 'app/entities/Karma/category';

@Component({
  selector: 'jhi-need-update',
  templateUrl: './need-update.component.html'
})
export class NeedUpdateComponent implements OnInit {
  need: INeed;
  isSaving: boolean;

  severities: ISeverity[];

  verificationteams: IVerificationTeam[];

  approvalstatuses: IApprovalStatus[];

  registeredusers: IRegisteredUser[];

  categories: ICategory[];
  date: string;

  constructor(
    private jhiAlertService: JhiAlertService,
    private needService: NeedService,
    private severityService: SeverityService,
    private verificationTeamService: VerificationTeamService,
    private approvalStatusService: ApprovalStatusService,
    private registeredUserService: RegisteredUserService,
    private categoryService: CategoryService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ need }) => {
      this.need = need;
      this.date = this.need.date != null ? this.need.date.format(DATE_TIME_FORMAT) : null;
    });
    this.severityService.query().subscribe(
      (res: HttpResponse<ISeverity[]>) => {
        this.severities = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.verificationTeamService.query().subscribe(
      (res: HttpResponse<IVerificationTeam[]>) => {
        this.verificationteams = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
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
    this.categoryService.query().subscribe(
      (res: HttpResponse<ICategory[]>) => {
        this.categories = res.body;
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    this.need.date = this.date != null ? moment(this.date, DATE_TIME_FORMAT) : null;
    if (this.need.id !== undefined) {
      this.subscribeToSaveResponse(this.needService.update(this.need));
    } else {
      this.subscribeToSaveResponse(this.needService.create(this.need));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<INeed>>) {
    result.subscribe((res: HttpResponse<INeed>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

  trackSeverityById(index: number, item: ISeverity) {
    return item.id;
  }

  trackVerificationTeamById(index: number, item: IVerificationTeam) {
    return item.id;
  }

  trackApprovalStatusById(index: number, item: IApprovalStatus) {
    return item.id;
  }

  trackRegisteredUserById(index: number, item: IRegisteredUser) {
    return item.id;
  }

  trackCategoryById(index: number, item: ICategory) {
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
