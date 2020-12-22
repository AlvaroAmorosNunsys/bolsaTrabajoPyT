import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { HistorialCandidaturaDetailComponent } from 'app/entities/historial-candidatura/historial-candidatura-detail.component';
import { HistorialCandidatura } from 'app/shared/model/historial-candidatura.model';

describe('Component Tests', () => {
  describe('HistorialCandidatura Management Detail Component', () => {
    let comp: HistorialCandidaturaDetailComponent;
    let fixture: ComponentFixture<HistorialCandidaturaDetailComponent>;
    const route = ({ data: of({ historialCandidatura: new HistorialCandidatura(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [HistorialCandidaturaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(HistorialCandidaturaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HistorialCandidaturaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load historialCandidatura on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.historialCandidatura).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
