/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { SeverityDetailComponent } from 'app/entities/Karma/severity/severity-detail.component';
import { Severity } from 'app/shared/model/Karma/severity.model';

describe('Component Tests', () => {
  describe('Severity Management Detail Component', () => {
    let comp: SeverityDetailComponent;
    let fixture: ComponentFixture<SeverityDetailComponent>;
    const route = ({ data: of({ severity: new Severity(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [SeverityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SeverityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SeverityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.severity).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
