/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { NeedDetailComponent } from 'app/entities/Karma/need/need-detail.component';
import { Need } from 'app/shared/model/Karma/need.model';

describe('Component Tests', () => {
  describe('Need Management Detail Component', () => {
    let comp: NeedDetailComponent;
    let fixture: ComponentFixture<NeedDetailComponent>;
    const route = ({ data: of({ need: new Need(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [NeedDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(NeedDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NeedDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.need).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
