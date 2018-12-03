import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIdentityProof } from 'app/shared/model/Karma/identity-proof.model';
import { IdentityProofService } from './identity-proof.service';

@Component({
  selector: 'jhi-identity-proof-delete-dialog',
  templateUrl: './identity-proof-delete-dialog.component.html'
})
export class IdentityProofDeleteDialogComponent {
  identityProof: IIdentityProof;

  constructor(
    private identityProofService: IdentityProofService,
    public activeModal: NgbActiveModal,
    private eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.identityProofService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'identityProofListModification',
        content: 'Deleted an identityProof'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-identity-proof-delete-popup',
  template: ''
})
export class IdentityProofDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ identityProof }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(IdentityProofDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.identityProof = identityProof;
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
