import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { FrontTestModule } from '../../../test.module';
import { HistorialCandidaturaComponent } from 'app/entities/historial-candidatura/historial-candidatura.component';
import { HistorialCandidaturaService } from 'app/entities/historial-candidatura/historial-candidatura.service';
import { HistorialCandidatura } from 'app/shared/model/historial-candidatura.model';

describe('Component Tests', () => {
  describe('HistorialCandidatura Management Component', () => {
    let comp: HistorialCandidaturaComponent;
    let fixture: ComponentFixture<HistorialCandidaturaComponent>;
    let service: HistorialCandidaturaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [HistorialCandidaturaComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: of({
                defaultSort: 'id,asc',
              }),
              queryParamMap: of(
                convertToParamMap({
                  page: '1',
                  size: '1',
                  sort: 'id,desc',
                })
              ),
            },
          },
        ],
      })
        .overrideTemplate(HistorialCandidaturaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HistorialCandidaturaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistorialCandidaturaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new HistorialCandidatura(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.historialCandidaturas && comp.historialCandidaturas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new HistorialCandidatura(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.historialCandidaturas && comp.historialCandidaturas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
