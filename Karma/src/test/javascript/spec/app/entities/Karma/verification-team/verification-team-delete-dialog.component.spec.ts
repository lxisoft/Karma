/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { KarmaTestModule } from '../../../../test.module';
import { VerificationTeamDeleteDialogComponent } from 'app/entities/Karma/verification-team/verification-team-delete-dialog.component';
import { VerificationTeamService } from 'app/entities/Karma/verification-team/verification-team.service';

describe('Component Tests', () => {
  describe('VerificationTeam Management Delete Component', () => {
    let comp: VerificationTeamDeleteDialogComponent;
    let fixture: ComponentFixture<VerificationTeamDeleteDialogComponent>;
    let service: VerificationTeamService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [VerificationTeamDeleteDialogComponent]
      })
        .overrideTemplate(VerificationTeamDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VerificationTeamDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VerificationTeamService);
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
