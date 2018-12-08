/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { FeedUpdateComponent } from 'app/entities/Karma/feed/feed-update.component';
import { FeedService } from 'app/entities/Karma/feed/feed.service';
import { Feed } from 'app/shared/model/Karma/feed.model';

describe('Component Tests', () => {
  describe('Feed Management Update Component', () => {
    let comp: FeedUpdateComponent;
    let fixture: ComponentFixture<FeedUpdateComponent>;
    let service: FeedService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [FeedUpdateComponent]
      })
        .overrideTemplate(FeedUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FeedUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FeedService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new Feed(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.feed = entity;
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
          const entity = new Feed();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.feed = entity;
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
