import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { EstadoCandidaturaUpdateComponent } from 'app/entities/estado-candidatura/estado-candidatura-update.component';
import { EstadoCandidaturaService } from 'app/entities/estado-candidatura/estado-candidatura.service';
import { EstadoCandidatura } from 'app/shared/model/estado-candidatura.model';

describe('Component Tests', () => {
  describe('EstadoCandidatura Management Update Component', () => {
    let comp: EstadoCandidaturaUpdateComponent;
    let fixture: ComponentFixture<EstadoCandidaturaUpdateComponent>;
    let service: EstadoCandidaturaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [EstadoCandidaturaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EstadoCandidaturaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadoCandidaturaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadoCandidaturaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadoCandidatura(123);
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
        const entity = new EstadoCandidatura();
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
