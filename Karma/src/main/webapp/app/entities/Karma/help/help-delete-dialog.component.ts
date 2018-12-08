import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHelp } from 'app/shared/model/Karma/help.model';
import { HelpService } from './help.service';

@Component({
  selector: 'jhi-help-delete-dialog',
  templateUrl: './help-delete-dialog.component.html'
})
export class HelpDeleteDialogComponent {
  help: IHelp;

  constructor(private helpService: HelpService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.helpService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'helpListModification',
        content: 'Deleted an help'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-help-delete-popup',
  template: ''
})
export class HelpDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ help }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(HelpDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.help = help;
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
