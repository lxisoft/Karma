import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApprovalStatus } from 'app/shared/model/Karma/approval-status.model';

@Component({
  selector: 'jhi-approval-status-detail',
  templateUrl: './approval-status-detail.component.html'
})
export class ApprovalStatusDetailComponent implements OnInit {
  approvalStatus: IApprovalStatus;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ approvalStatus }) => {
      this.approvalStatus = approvalStatus;
    });
  }

  previousState() {
    window.history.back();
  }
}
