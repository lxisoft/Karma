/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KarmaTestModule } from '../../../../test.module';
import { FeedDeleteDialogComponent } from 'app/entities/Karma/feed/feed-delete-dialog.component';
import { FeedService } from 'app/entities/Karma/feed/feed.service';

describe('Component Tests', () => {
  describe('Feed Management Delete Component', () => {
    let comp: FeedDeleteDialogComponent;
    let fixture: ComponentFixture<FeedDeleteDialogComponent>;
    let service: FeedService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [FeedDeleteDialogComponent]
      })
        .overrideTemplate(FeedDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FeedDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FeedService);
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
