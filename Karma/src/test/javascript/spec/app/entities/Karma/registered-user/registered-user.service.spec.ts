/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { RegisteredUserService } from 'app/entities/Karma/registered-user/registered-user.service';
import { IRegisteredUser, RegisteredUser } from 'app/shared/model/Karma/registered-user.model';

describe('Service Tests', () => {
  describe('RegisteredUser Service', () => {
    let injector: TestBed;
    let service: RegisteredUserService;
    let httpMock: HttpTestingController;
    let elemDefault: IRegisteredUser;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      injector = getTestBed();
      service = injector.get(RegisteredUserService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new RegisteredUser(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        0,
        0,
        0
      );
    });

    describe('Service methods', async () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            dob: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(JSON.stringify(returnedFromService));
      });

      it('should create a RegisteredUser', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dob: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dob: currentDate
          },
          returnedFromService
        );
        service
          .create(new RegisteredUser(null))
          .pipe(take(1))
          .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(JSON.stringify(returnedFromService));
      });

      it('should update a RegisteredUser', async () => {
        const returnedFromService = Object.assign(
          {
            email: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            rating: 1,
            description: 'BBBBBB',
            profession: 'BBBBBB',
            gender: 'BBBBBB',
            dob: currentDate.format(DATE_FORMAT),
            bloodGroup: 'BBBBBB',
            emotionalQuotient: 1,
            socialQuotient: 1,
            happinessIndex: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dob: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(JSON.stringify(returnedFromService));
      });

      it('should return a list of RegisteredUser', async () => {
        const returnedFromService = Object.assign(
          {
            email: 'BBBBBB',
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            rating: 1,
            description: 'BBBBBB',
            profession: 'BBBBBB',
            gender: 'BBBBBB',
            dob: currentDate.format(DATE_FORMAT),
            bloodGroup: 'BBBBBB',
            emotionalQuotient: 1,
            socialQuotient: 1,
            happinessIndex: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            dob: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => expect(body).toContainEqual(expected));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(JSON.stringify([returnedFromService]));
        httpMock.verify();
      });

      it('should delete a RegisteredUser', async () => {
        const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
