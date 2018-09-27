/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LibraryTestModule } from '../../../test.module';
import { PublisherComponent } from 'app/entities/publisher/publisher.component';
import { PublisherService } from 'app/entities/publisher/publisher.service';
import { Publisher } from 'app/shared/model/publisher.model';

describe('Component Tests', () => {
    describe('Publisher Management Component', () => {
        let comp: PublisherComponent;
        let fixture: ComponentFixture<PublisherComponent>;
        let service: PublisherService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LibraryTestModule],
                declarations: [PublisherComponent],
                providers: []
            })
                .overrideTemplate(PublisherComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PublisherComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PublisherService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Publisher(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.publishers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
