/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KarmaTestModule } from '../../../../test.module';
import { ReplyDeleteDialogComponent } from 'app/entities/Karma/reply/reply-delete-dialog.component';
import { ReplyService } from 'app/entities/Karma/reply/reply.service';

describe('Component Tests', () => {
  describe('Reply Management Delete Component', () => {
    let comp: ReplyDeleteDialogComponent;
    let fixture: ComponentFixture<ReplyDeleteDialogComponent>;
    let service: ReplyService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [ReplyDeleteDialogComponent]
      })
        .overrideTemplate(ReplyDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReplyDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReplyService);
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
