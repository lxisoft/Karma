/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { IdentityProofDetailComponent } from 'app/entities/Karma/identity-proof/identity-proof-detail.component';
import { IdentityProof } from 'app/shared/model/Karma/identity-proof.model';

describe('Component Tests', () => {
  describe('IdentityProof Management Detail Component', () => {
    let comp: IdentityProofDetailComponent;
    let fixture: ComponentFixture<IdentityProofDetailComponent>;
    const route = ({ data: of({ identityProof: new IdentityProof(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [IdentityProofDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(IdentityProofDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IdentityProofDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.identityProof).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
