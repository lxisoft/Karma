/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KarmaTestModule } from '../../../../test.module';
import { ApprovalStatusDeleteDialogComponent } from 'app/entities/Karma/approval-status/approval-status-delete-dialog.component';
import { ApprovalStatusService } from 'app/entities/Karma/approval-status/approval-status.service';

describe('Component Tests', () => {
  describe('ApprovalStatus Management Delete Component', () => {
    let comp: ApprovalStatusDeleteDialogComponent;
    let fixture: ComponentFixture<ApprovalStatusDeleteDialogComponent>;
    let service: ApprovalStatusService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [ApprovalStatusDeleteDialogComponent]
      })
        .overrideTemplate(ApprovalStatusDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApprovalStatusDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApprovalStatusService);
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
