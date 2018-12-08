import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVerificationTeam } from 'app/shared/model/Karma/verification-team.model';
import { VerificationTeamService } from './verification-team.service';

@Component({
  selector: 'jhi-verification-team-delete-dialog',
  templateUrl: './verification-team-delete-dialog.component.html'
})
export class VerificationTeamDeleteDialogComponent {
  verificationTeam: IVerificationTeam;

  constructor(
    private verificationTeamService: VerificationTeamService,
    public activeModal: NgbActiveModal,
    private eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.verificationTeamService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'verificationTeamListModification',
        content: 'Deleted an verificationTeam'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-verification-team-delete-popup',
  template: ''
})
export class VerificationTeamDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ verificationTeam }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(VerificationTeamDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.verificationTeam = verificationTeam;
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
