import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApprovalStatus } from 'app/shared/model/Karma/approval-status.model';
import { ApprovalStatusService } from './approval-status.service';

@Component({
  selector: 'jhi-approval-status-delete-dialog',
  templateUrl: './approval-status-delete-dialog.component.html'
})
export class ApprovalStatusDeleteDialogComponent {
  approvalStatus: IApprovalStatus;

  constructor(
    private approvalStatusService: ApprovalStatusService,
    public activeModal: NgbActiveModal,
    private eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.approvalStatusService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'approvalStatusListModification',
        content: 'Deleted an approvalStatus'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-approval-status-delete-popup',
  template: ''
})
export class ApprovalStatusDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ approvalStatus }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ApprovalStatusDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.approvalStatus = approvalStatus;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
