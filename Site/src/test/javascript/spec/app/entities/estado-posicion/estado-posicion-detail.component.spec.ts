import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { EstadoPosicionDetailComponent } from 'app/entities/estado-posicion/estado-posicion-detail.component';
import { EstadoPosicion } from 'app/shared/model/estado-posicion.model';

describe('Component Tests', () => {
  describe('EstadoPosicion Management Detail Component', () => {
    let comp: EstadoPosicionDetailComponent;
    let fixture: ComponentFixture<EstadoPosicionDetailComponent>;
    const route = ({ data: of({ estadoPosicion: new EstadoPosicion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [EstadoPosicionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EstadoPosicionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadoPosicionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadoPosicion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadoPosicion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
