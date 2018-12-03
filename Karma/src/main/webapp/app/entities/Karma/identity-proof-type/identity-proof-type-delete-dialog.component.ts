import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIdentityProofType } from 'app/shared/model/Karma/identity-proof-type.model';
import { IdentityProofTypeService } from './identity-proof-type.service';

@Component({
  selector: 'jhi-identity-proof-type-delete-dialog',
  templateUrl: './identity-proof-type-delete-dialog.component.html'
})
export class IdentityProofTypeDeleteDialogComponent {
  identityProofType: IIdentityProofType;

  constructor(
    private identityProofTypeService: IdentityProofTypeService,
    public activeModal: NgbActiveModal,
    private eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.identityProofTypeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'identityProofTypeListModification',
        content: 'Deleted an identityProofType'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-identity-proof-type-delete-popup',
  template: ''
})
export class IdentityProofTypeDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ identityProofType }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(IdentityProofTypeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.identityProofType = identityProofType;
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
