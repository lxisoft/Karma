import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INeed } from 'app/shared/model/Karma/need.model';
import { NeedService } from './need.service';

@Component({
  selector: 'jhi-need-delete-dialog',
  templateUrl: './need-delete-dialog.component.html'
})
export class NeedDeleteDialogComponent {
  need: INeed;

  constructor(private needService: NeedService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.needService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'needListModification',
        content: 'Deleted an need'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-need-delete-popup',
  template: ''
})
export class NeedDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ need }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(NeedDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.need = need;
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
