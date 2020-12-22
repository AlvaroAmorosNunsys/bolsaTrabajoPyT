import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { EstadoPosicionUpdateComponent } from 'app/entities/estado-posicion/estado-posicion-update.component';
import { EstadoPosicionService } from 'app/entities/estado-posicion/estado-posicion.service';
import { EstadoPosicion } from 'app/shared/model/estado-posicion.model';

describe('Component Tests', () => {
  describe('EstadoPosicion Management Update Component', () => {
    let comp: EstadoPosicionUpdateComponent;
    let fixture: ComponentFixture<EstadoPosicionUpdateComponent>;
    let service: EstadoPosicionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [EstadoPosicionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EstadoPosicionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadoPosicionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadoPosicionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadoPosicion(123);
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
        const entity = new EstadoPosicion();
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
