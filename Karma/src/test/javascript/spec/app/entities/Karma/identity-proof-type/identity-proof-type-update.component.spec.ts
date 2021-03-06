/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { IdentityProofTypeUpdateComponent } from 'app/entities/Karma/identity-proof-type/identity-proof-type-update.component';
import { IdentityProofTypeService } from 'app/entities/Karma/identity-proof-type/identity-proof-type.service';
import { IdentityProofType } from 'app/shared/model/Karma/identity-proof-type.model';

describe('Component Tests', () => {
  describe('IdentityProofType Management Update Component', () => {
    let comp: IdentityProofTypeUpdateComponent;
    let fixture: ComponentFixture<IdentityProofTypeUpdateComponent>;
    let service: IdentityProofTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [IdentityProofTypeUpdateComponent]
      })
        .overrideTemplate(IdentityProofTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(IdentityProofTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IdentityProofTypeService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new IdentityProofType(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.identityProofType = entity;
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
          const entity = new IdentityProofType();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.identityProofType = entity;
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
