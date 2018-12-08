/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { VerificationTeamDetailComponent } from 'app/entities/Karma/verification-team/verification-team-detail.component';
import { VerificationTeam } from 'app/shared/model/Karma/verification-team.model';

describe('Component Tests', () => {
  describe('VerificationTeam Management Detail Component', () => {
    let comp: VerificationTeamDetailComponent;
    let fixture: ComponentFixture<VerificationTeamDetailComponent>;
    const route = ({ data: of({ verificationTeam: new VerificationTeam(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [VerificationTeamDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(VerificationTeamDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VerificationTeamDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.verificationTeam).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
