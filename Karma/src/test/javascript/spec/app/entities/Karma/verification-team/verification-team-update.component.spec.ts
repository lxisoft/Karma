/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { VerificationTeamUpdateComponent } from 'app/entities/Karma/verification-team/verification-team-update.component';
import { VerificationTeamService } from 'app/entities/Karma/verification-team/verification-team.service';
import { VerificationTeam } from 'app/shared/model/Karma/verification-team.model';

describe('Component Tests', () => {
  describe('VerificationTeam Management Update Component', () => {
    let comp: VerificationTeamUpdateComponent;
    let fixture: ComponentFixture<VerificationTeamUpdateComponent>;
    let service: VerificationTeamService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [VerificationTeamUpdateComponent]
      })
        .overrideTemplate(VerificationTeamUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VerificationTeamUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VerificationTeamService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new VerificationTeam(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.verificationTeam = entity;
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
          const entity = new VerificationTeam();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.verificationTeam = entity;
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
