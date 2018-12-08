/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KarmaTestModule } from '../../../../test.module';
import { IdentityProofDeleteDialogComponent } from 'app/entities/Karma/identity-proof/identity-proof-delete-dialog.component';
import { IdentityProofService } from 'app/entities/Karma/identity-proof/identity-proof.service';

describe('Component Tests', () => {
  describe('IdentityProof Management Delete Component', () => {
    let comp: IdentityProofDeleteDialogComponent;
    let fixture: ComponentFixture<IdentityProofDeleteDialogComponent>;
    let service: IdentityProofService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [IdentityProofDeleteDialogComponent]
      })
        .overrideTemplate(IdentityProofDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IdentityProofDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IdentityProofService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
