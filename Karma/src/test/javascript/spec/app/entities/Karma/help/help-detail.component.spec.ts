/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { HelpDetailComponent } from 'app/entities/Karma/help/help-detail.component';
import { Help } from 'app/shared/model/Karma/help.model';

describe('Component Tests', () => {
  describe('Help Management Detail Component', () => {
    let comp: HelpDetailComponent;
    let fixture: ComponentFixture<HelpDetailComponent>;
    const route = ({ data: of({ help: new Help(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [HelpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(HelpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HelpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.help).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
