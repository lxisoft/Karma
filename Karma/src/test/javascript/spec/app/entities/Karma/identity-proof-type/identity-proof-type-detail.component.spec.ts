/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { IdentityProofTypeDetailComponent } from 'app/entities/Karma/identity-proof-type/identity-proof-type-detail.component';
import { IdentityProofType } from 'app/shared/model/Karma/identity-proof-type.model';

describe('Component Tests', () => {
  describe('IdentityProofType Management Detail Component', () => {
    let comp: IdentityProofTypeDetailComponent;
    let fixture: ComponentFixture<IdentityProofTypeDetailComponent>;
    const route = ({ data: of({ identityProofType: new IdentityProofType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [IdentityProofTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(IdentityProofTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(IdentityProofTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.identityProofType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
