import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { UnidadDeNegocioDetailComponent } from 'app/entities/unidad-de-negocio/unidad-de-negocio-detail.component';
import { UnidadDeNegocio } from 'app/shared/model/unidad-de-negocio.model';

describe('Component Tests', () => {
  describe('UnidadDeNegocio Management Detail Component', () => {
    let comp: UnidadDeNegocioDetailComponent;
    let fixture: ComponentFixture<UnidadDeNegocioDetailComponent>;
    const route = ({ data: of({ unidadDeNegocio: new UnidadDeNegocio(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [UnidadDeNegocioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(UnidadDeNegocioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UnidadDeNegocioDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load unidadDeNegocio on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.unidadDeNegocio).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
