import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserCheck } from 'app/shared/model/Karma/user-check.model';
import { UserCheckService } from './user-check.service';

@Component({
  selector: 'jhi-user-check-delete-dialog',
  templateUrl: './user-check-delete-dialog.component.html'
})
export class UserCheckDeleteDialogComponent {
  userCheck: IUserCheck;

  constructor(private userCheckService: UserCheckService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.userCheckService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'userCheckListModification',
        content: 'Deleted an userCheck'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-user-check-delete-popup',
  template: ''
})
export class UserCheckDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userCheck }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(UserCheckDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.userCheck = userCheck;
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
