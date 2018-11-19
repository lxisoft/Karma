import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IApprovalStatus } from 'app/shared/model/Karma/approval-status.model';
import { ApprovalStatusService } from './approval-status.service';

@Component({
  selector: 'jhi-approval-status-update',
  templateUrl: './approval-status-update.component.html'
})
export class ApprovalStatusUpdateComponent implements OnInit {
  approvalStatus: IApprovalStatus;
  isSaving: boolean;

  constructor(private approvalStatusService: ApprovalStatusService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ approvalStatus }) => {
      this.approvalStatus = approvalStatus;
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    if (this.approvalStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.approvalStatusService.update(this.approvalStatus));
    } else {
      this.subscribeToSaveResponse(this.approvalStatusService.create(this.approvalStatus));
    }
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<IApprovalStatus>>) {
    result.subscribe((res: HttpResponse<IApprovalStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  private onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError() {
    this.isSaving = false;
  }
}
