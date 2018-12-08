/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { HelpUpdateComponent } from 'app/entities/Karma/help/help-update.component';
import { HelpService } from 'app/entities/Karma/help/help.service';
import { Help } from 'app/shared/model/Karma/help.model';

describe('Component Tests', () => {
  describe('Help Management Update Component', () => {
    let comp: HelpUpdateComponent;
    let fixture: ComponentFixture<HelpUpdateComponent>;
    let service: HelpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [HelpUpdateComponent]
      })
        .overrideTemplate(HelpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HelpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HelpService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new Help(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.help = entity;
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
          const entity = new Help();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.help = entity;
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
