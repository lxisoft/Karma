/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KarmaTestModule } from '../../../../test.module';
import { PostDetailComponent } from 'app/entities/Karma/post/post-detail.component';
import { Post } from 'app/shared/model/Karma/post.model';

describe('Component Tests', () => {
  describe('Post Management Detail Component', () => {
    let comp: PostDetailComponent;
    let fixture: ComponentFixture<PostDetailComponent>;
    const route = ({ data: of({ post: new Post(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [KarmaTestModule],
        declarations: [PostDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PostDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PostDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.post).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
