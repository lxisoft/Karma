/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { ApprovalStatusDetailComponent } from 'app/entities/Karma/approval-status/approval-status-detail.component';
import { ApprovalStatus } from 'app/shared/model/Karma/approval-status.model';

describe('Component Tests', () => {
  describe('ApprovalStatus Management Detail Component', () => {
    let comp: ApprovalStatusDetailComponent;
    let fixture: ComponentFixture<ApprovalStatusDetailComponent>;
    const route = ({ data: of({ approvalStatus: new ApprovalStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [ApprovalStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ApprovalStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApprovalStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.approvalStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
