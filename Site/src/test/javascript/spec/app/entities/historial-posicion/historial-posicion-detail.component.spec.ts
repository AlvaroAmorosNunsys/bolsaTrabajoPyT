import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { HistorialPosicionDetailComponent } from 'app/entities/historial-posicion/historial-posicion-detail.component';
import { HistorialPosicion } from 'app/shared/model/historial-posicion.model';

describe('Component Tests', () => {
  describe('HistorialPosicion Management Detail Component', () => {
    let comp: HistorialPosicionDetailComponent;
    let fixture: ComponentFixture<HistorialPosicionDetailComponent>;
    const route = ({ data: of({ historialPosicion: new HistorialPosicion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [HistorialPosicionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(HistorialPosicionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HistorialPosicionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load historialPosicion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.historialPosicion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
