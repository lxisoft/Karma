import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedia } from 'app/shared/model/Karma/media.model';
import { MediaService } from './media.service';

@Component({
  selector: 'jhi-media-delete-dialog',
  templateUrl: './media-delete-dialog.component.html'
})
export class MediaDeleteDialogComponent {
  media: IMedia;

  constructor(private mediaService: MediaService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mediaService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mediaListModification',
        content: 'Deleted an media'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-media-delete-popup',
  template: ''
})
export class MediaDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ media }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MediaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.media = media;
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
