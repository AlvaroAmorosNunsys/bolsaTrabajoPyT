import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { HistorialCandidaturaUpdateComponent } from 'app/entities/historial-candidatura/historial-candidatura-update.component';
import { HistorialCandidaturaService } from 'app/entities/historial-candidatura/historial-candidatura.service';
import { HistorialCandidatura } from 'app/shared/model/historial-candidatura.model';

describe('Component Tests', () => {
  describe('HistorialCandidatura Management Update Component', () => {
    let comp: HistorialCandidaturaUpdateComponent;
    let fixture: ComponentFixture<HistorialCandidaturaUpdateComponent>;
    let service: HistorialCandidaturaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [HistorialCandidaturaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(HistorialCandidaturaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HistorialCandidaturaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistorialCandidaturaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new HistorialCandidatura(123);
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
        const entity = new HistorialCandidatura();
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
