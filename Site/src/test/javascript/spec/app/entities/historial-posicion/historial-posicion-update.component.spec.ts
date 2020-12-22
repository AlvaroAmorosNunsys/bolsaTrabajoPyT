import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { HistorialPosicionUpdateComponent } from 'app/entities/historial-posicion/historial-posicion-update.component';
import { HistorialPosicionService } from 'app/entities/historial-posicion/historial-posicion.service';
import { HistorialPosicion } from 'app/shared/model/historial-posicion.model';

describe('Component Tests', () => {
  describe('HistorialPosicion Management Update Component', () => {
    let comp: HistorialPosicionUpdateComponent;
    let fixture: ComponentFixture<HistorialPosicionUpdateComponent>;
    let service: HistorialPosicionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [HistorialPosicionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(HistorialPosicionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HistorialPosicionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistorialPosicionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new HistorialPosicion(123);
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
        const entity = new HistorialPosicion();
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
