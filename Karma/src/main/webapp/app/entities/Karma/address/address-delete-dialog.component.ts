import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAddress } from 'app/shared/model/Karma/address.model';
import { AddressService } from './address.service';

@Component({
  selector: 'jhi-address-delete-dialog',
  templateUrl: './address-delete-dialog.component.html'
})
export class AddressDeleteDialogComponent {
  address: IAddress;

  constructor(private addressService: AddressService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.addressService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'addressListModification',
        content: 'Deleted an address'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-address-delete-popup',
  template: ''
})
export class AddressDeletePopupComponent implements OnInit, OnDestroy {
  private ngbModalRef: NgbModalRef;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ address }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AddressDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.address = address;
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
