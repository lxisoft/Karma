/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { RegisteredUserUpdateComponent } from 'app/entities/Karma/registered-user/registered-user-update.component';
import { RegisteredUserService } from 'app/entities/Karma/registered-user/registered-user.service';
import { RegisteredUser } from 'app/shared/model/Karma/registered-user.model';

describe('Component Tests', () => {
  describe('RegisteredUser Management Update Component', () => {
    let comp: RegisteredUserUpdateComponent;
    let fixture: ComponentFixture<RegisteredUserUpdateComponent>;
    let service: RegisteredUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [RegisteredUserUpdateComponent]
      })
        .overrideTemplate(RegisteredUserUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RegisteredUserUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RegisteredUserService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new RegisteredUser(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.registeredUser = entity;
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
          const entity = new RegisteredUser();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.registeredUser = entity;
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
