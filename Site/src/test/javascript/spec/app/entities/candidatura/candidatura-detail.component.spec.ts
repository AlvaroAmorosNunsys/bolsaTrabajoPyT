import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { CandidaturaDetailComponent } from 'app/entities/candidatura/candidatura-detail.component';
import { Candidatura } from 'app/shared/model/candidatura.model';

describe('Component Tests', () => {
  describe('Candidatura Management Detail Component', () => {
    let comp: CandidaturaDetailComponent;
    let fixture: ComponentFixture<CandidaturaDetailComponent>;
    const route = ({ data: of({ candidatura: new Candidatura(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [CandidaturaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CandidaturaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CandidaturaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load candidatura on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.candidatura).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
