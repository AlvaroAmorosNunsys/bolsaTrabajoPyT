import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { TipoJornadaDetailComponent } from 'app/entities/tipo-jornada/tipo-jornada-detail.component';
import { TipoJornada } from 'app/shared/model/tipo-jornada.model';

describe('Component Tests', () => {
  describe('TipoJornada Management Detail Component', () => {
    let comp: TipoJornadaDetailComponent;
    let fixture: ComponentFixture<TipoJornadaDetailComponent>;
    const route = ({ data: of({ tipoJornada: new TipoJornada(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [TipoJornadaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TipoJornadaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TipoJornadaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tipoJornada on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tipoJornada).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
