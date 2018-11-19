/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { ReplyDetailComponent } from 'app/entities/Karma/reply/reply-detail.component';
import { Reply } from 'app/shared/model/Karma/reply.model';

describe('Component Tests', () => {
  describe('Reply Management Detail Component', () => {
    let comp: ReplyDetailComponent;
    let fixture: ComponentFixture<ReplyDetailComponent>;
    const route = ({ data: of({ reply: new Reply(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [ReplyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ReplyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReplyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.reply).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
