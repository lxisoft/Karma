/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { MediaUpdateComponent } from 'app/entities/Karma/media/media-update.component';
import { MediaService } from 'app/entities/Karma/media/media.service';
import { Media } from 'app/shared/model/Karma/media.model';

describe('Component Tests', () => {
  describe('Media Management Update Component', () => {
    let comp: MediaUpdateComponent;
    let fixture: ComponentFixture<MediaUpdateComponent>;
    let service: MediaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [MediaUpdateComponent]
      })
        .overrideTemplate(MediaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MediaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MediaService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new Media(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.media = entity;
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
          const entity = new Media();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.media = entity;
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
