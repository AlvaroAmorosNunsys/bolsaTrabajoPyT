import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { CandidaturaUpdateComponent } from 'app/entities/candidatura/candidatura-update.component';
import { CandidaturaService } from 'app/entities/candidatura/candidatura.service';
import { Candidatura } from 'app/shared/model/candidatura.model';

describe('Component Tests', () => {
  describe('Candidatura Management Update Component', () => {
    let comp: CandidaturaUpdateComponent;
    let fixture: ComponentFixture<CandidaturaUpdateComponent>;
    let service: CandidaturaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [CandidaturaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CandidaturaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CandidaturaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CandidaturaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Candidatura(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Candidatura();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
