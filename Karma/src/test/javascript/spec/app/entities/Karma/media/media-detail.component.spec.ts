/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { MediaDetailComponent } from 'app/entities/Karma/media/media-detail.component';
import { Media } from 'app/shared/model/Karma/media.model';

describe('Component Tests', () => {
  describe('Media Management Detail Component', () => {
    let comp: MediaDetailComponent;
    let fixture: ComponentFixture<MediaDetailComponent>;
    const route = ({ data: of({ media: new Media(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [MediaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MediaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MediaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.media).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
