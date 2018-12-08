/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { UserCheckDetailComponent } from 'app/entities/Karma/user-check/user-check-detail.component';
import { UserCheck } from 'app/shared/model/Karma/user-check.model';

describe('Component Tests', () => {
  describe('UserCheck Management Detail Component', () => {
    let comp: UserCheckDetailComponent;
    let fixture: ComponentFixture<UserCheckDetailComponent>;
    const route = ({ data: of({ userCheck: new UserCheck(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [UserCheckDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UserCheckDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserCheckDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userCheck).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
