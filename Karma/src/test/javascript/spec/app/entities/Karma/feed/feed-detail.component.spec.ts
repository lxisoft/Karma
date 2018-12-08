/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { FeedDetailComponent } from 'app/entities/Karma/feed/feed-detail.component';
import { Feed } from 'app/shared/model/Karma/feed.model';

describe('Component Tests', () => {
  describe('Feed Management Detail Component', () => {
    let comp: FeedDetailComponent;
    let fixture: ComponentFixture<FeedDetailComponent>;
    const route = ({ data: of({ feed: new Feed(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [FeedDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FeedDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FeedDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.feed).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
