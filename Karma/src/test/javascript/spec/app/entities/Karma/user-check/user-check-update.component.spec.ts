/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { UserCheckUpdateComponent } from 'app/entities/Karma/user-check/user-check-update.component';
import { UserCheckService } from 'app/entities/Karma/user-check/user-check.service';
import { UserCheck } from 'app/shared/model/Karma/user-check.model';

describe('Component Tests', () => {
  describe('UserCheck Management Update Component', () => {
    let comp: UserCheckUpdateComponent;
    let fixture: ComponentFixture<UserCheckUpdateComponent>;
    let service: UserCheckService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [UserCheckUpdateComponent]
      })
        .overrideTemplate(UserCheckUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserCheckUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserCheckService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new UserCheck(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.userCheck = entity;
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
          const entity = new UserCheck();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.userCheck = entity;
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
