/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KarmaTestModule } from '../../../../test.module';
import { RegisteredUserDeleteDialogComponent } from 'app/entities/Karma/registered-user/registered-user-delete-dialog.component';
import { RegisteredUserService } from 'app/entities/Karma/registered-user/registered-user.service';

describe('Component Tests', () => {
  describe('RegisteredUser Management Delete Component', () => {
    let comp: RegisteredUserDeleteDialogComponent;
    let fixture: ComponentFixture<RegisteredUserDeleteDialogComponent>;
    let service: RegisteredUserService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [RegisteredUserDeleteDialogComponent]
      })
        .overrideTemplate(RegisteredUserDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RegisteredUserDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RegisteredUserService);
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
