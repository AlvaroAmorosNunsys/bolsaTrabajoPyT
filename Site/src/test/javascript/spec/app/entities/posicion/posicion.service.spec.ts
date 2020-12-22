import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { PosicionService } from 'app/entities/posicion/posicion.service';
import { IPosicion, Posicion } from 'app/shared/model/posicion.model';

describe('Service Tests', () => {
  describe('Posicion Service', () => {
    let injector: TestBed;
    let service: PosicionService;
    let httpMock: HttpTestingController;
    let elemDefault: IPosicion;
    let expectedResult: IPosicion | IPosicion[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PosicionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Posicion(0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechaAlta: currentDate.format(DATE_FORMAT),
            fechaNecesidad: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Posicion', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaAlta: currentDate.format(DATE_FORMAT),
            fechaNecesidad: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaAlta: currentDate,
            fechaNecesidad: currentDate,
          },
          returnedFromService
        );

        service.create(new Posicion()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Posicion', () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            descripcion: 'BBBBBB',
            numeroPuestos: 1,
            salarioMinimo: 1,
            salarioMaximo: 1,
            fechaAlta: currentDate.format(DATE_FORMAT),
            fechaNecesidad: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaAlta: currentDate,
            fechaNecesidad: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Posicion', () => {
        const returnedFromService = Object.assign(
          {
            titulo: 'BBBBBB',
            descripcion: 'BBBBBB',
            numeroPuestos: 1,
            salarioMinimo: 1,
            salarioMaximo: 1,
            fechaAlta: currentDate.format(DATE_FORMAT),
            fechaNecesidad: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaAlta: currentDate,
            fechaNecesidad: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Posicion', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
