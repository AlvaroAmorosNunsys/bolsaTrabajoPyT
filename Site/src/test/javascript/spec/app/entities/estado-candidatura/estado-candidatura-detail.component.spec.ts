import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { EstadoCandidaturaDetailComponent } from 'app/entities/estado-candidatura/estado-candidatura-detail.component';
import { EstadoCandidatura } from 'app/shared/model/estado-candidatura.model';

describe('Component Tests', () => {
  describe('EstadoCandidatura Management Detail Component', () => {
    let comp: EstadoCandidaturaDetailComponent;
    let fixture: ComponentFixture<EstadoCandidaturaDetailComponent>;
    const route = ({ data: of({ estadoCandidatura: new EstadoCandidatura(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [EstadoCandidaturaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EstadoCandidaturaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadoCandidaturaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadoCandidatura on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadoCandidatura).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
