/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { SeverityUpdateComponent } from 'app/entities/Karma/severity/severity-update.component';
import { SeverityService } from 'app/entities/Karma/severity/severity.service';
import { Severity } from 'app/shared/model/Karma/severity.model';

describe('Component Tests', () => {
  describe('Severity Management Update Component', () => {
    let comp: SeverityUpdateComponent;
    let fixture: ComponentFixture<SeverityUpdateComponent>;
    let service: SeverityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [SeverityUpdateComponent]
      })
        .overrideTemplate(SeverityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SeverityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SeverityService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new Severity(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.severity = entity;
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
          const entity = new Severity();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.severity = entity;
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
