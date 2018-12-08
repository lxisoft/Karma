/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KarmaTestModule } from '../../../../test.module';
import { IdentityProofTypeDeleteDialogComponent } from 'app/entities/Karma/identity-proof-type/identity-proof-type-delete-dialog.component';
import { IdentityProofTypeService } from 'app/entities/Karma/identity-proof-type/identity-proof-type.service';

describe('Component Tests', () => {
  describe('IdentityProofType Management Delete Component', () => {
    let comp: IdentityProofTypeDeleteDialogComponent;
    let fixture: ComponentFixture<IdentityProofTypeDeleteDialogComponent>;
    let service: IdentityProofTypeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [IdentityProofTypeDeleteDialogComponent]
      })
        .overrideTemplate(IdentityProofTypeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IdentityProofTypeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IdentityProofTypeService);
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
