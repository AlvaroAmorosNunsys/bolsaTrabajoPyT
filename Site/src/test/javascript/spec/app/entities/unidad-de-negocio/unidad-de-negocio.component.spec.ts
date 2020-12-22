import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap } from '@angular/router';

import { FrontTestModule } from '../../../test.module';
import { UnidadDeNegocioComponent } from 'app/entities/unidad-de-negocio/unidad-de-negocio.component';
import { UnidadDeNegocioService } from 'app/entities/unidad-de-negocio/unidad-de-negocio.service';
import { UnidadDeNegocio } from 'app/shared/model/unidad-de-negocio.model';

describe('Component Tests', () => {
  describe('UnidadDeNegocio Management Component', () => {
    let comp: UnidadDeNegocioComponent;
    let fixture: ComponentFixture<UnidadDeNegocioComponent>;
    let service: UnidadDeNegocioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [UnidadDeNegocioComponent],
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
        .overrideTemplate(UnidadDeNegocioComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UnidadDeNegocioComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UnidadDeNegocioService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UnidadDeNegocio(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.unidadDeNegocios && comp.unidadDeNegocios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UnidadDeNegocio(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.unidadDeNegocios && comp.unidadDeNegocios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
