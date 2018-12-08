/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { IdentityProofUpdateComponent } from 'app/entities/Karma/identity-proof/identity-proof-update.component';
import { IdentityProofService } from 'app/entities/Karma/identity-proof/identity-proof.service';
import { IdentityProof } from 'app/shared/model/Karma/identity-proof.model';

describe('Component Tests', () => {
  describe('IdentityProof Management Update Component', () => {
    let comp: IdentityProofUpdateComponent;
    let fixture: ComponentFixture<IdentityProofUpdateComponent>;
    let service: IdentityProofService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [IdentityProofUpdateComponent]
      })
        .overrideTemplate(IdentityProofUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(IdentityProofUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(IdentityProofService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new IdentityProof(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.identityProof = entity;
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
          const entity = new IdentityProof();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.identityProof = entity;
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
