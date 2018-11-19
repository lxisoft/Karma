/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { ApprovalStatusUpdateComponent } from 'app/entities/Karma/approval-status/approval-status-update.component';
import { ApprovalStatusService } from 'app/entities/Karma/approval-status/approval-status.service';
import { ApprovalStatus } from 'app/shared/model/Karma/approval-status.model';

describe('Component Tests', () => {
  describe('ApprovalStatus Management Update Component', () => {
    let comp: ApprovalStatusUpdateComponent;
    let fixture: ComponentFixture<ApprovalStatusUpdateComponent>;
    let service: ApprovalStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [ApprovalStatusUpdateComponent]
      })
        .overrideTemplate(ApprovalStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApprovalStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApprovalStatusService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new ApprovalStatus(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.approvalStatus = entity;
          // WHEN
          comp.save();
          tick(); // simulate async

          // THEN
          expect(service.update).toHaveBeenCalledWith(entity);
          expect(comp.isSaving).toEqual(false);
        })
      );

      it(
        'Should call create service on save for new entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new ApprovalStatus();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.approvalStatus = entity;
          // WHEN
          comp.save();
          tick(); // simulate async

          // THEN
          expect(service.create).toHaveBeenCalledWith(entity);
          expect(comp.isSaving).toEqual(false);
        })
      );
    });
  });
});
