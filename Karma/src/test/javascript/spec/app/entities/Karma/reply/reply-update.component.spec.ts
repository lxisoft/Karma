/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { ReplyUpdateComponent } from 'app/entities/Karma/reply/reply-update.component';
import { ReplyService } from 'app/entities/Karma/reply/reply.service';
import { Reply } from 'app/shared/model/Karma/reply.model';

describe('Component Tests', () => {
  describe('Reply Management Update Component', () => {
    let comp: ReplyUpdateComponent;
    let fixture: ComponentFixture<ReplyUpdateComponent>;
    let service: ReplyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [ReplyUpdateComponent]
      })
        .overrideTemplate(ReplyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReplyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReplyService);
    });

    describe('save', () => {
      it(
        'Should call update service on save for existing entity',
        fakeAsync(() => {
          // GIVEN
          const entity = new Reply(123);
          spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.reply = entity;
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
          const entity = new Reply();
          spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
          comp.reply = entity;
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
