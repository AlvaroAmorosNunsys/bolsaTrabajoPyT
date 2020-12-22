import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { UnidadDeNegocioUpdateComponent } from 'app/entities/unidad-de-negocio/unidad-de-negocio-update.component';
import { UnidadDeNegocioService } from 'app/entities/unidad-de-negocio/unidad-de-negocio.service';
import { UnidadDeNegocio } from 'app/shared/model/unidad-de-negocio.model';

describe('Component Tests', () => {
  describe('UnidadDeNegocio Management Update Component', () => {
    let comp: UnidadDeNegocioUpdateComponent;
    let fixture: ComponentFixture<UnidadDeNegocioUpdateComponent>;
    let service: UnidadDeNegocioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [UnidadDeNegocioUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(UnidadDeNegocioUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UnidadDeNegocioUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UnidadDeNegocioService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UnidadDeNegocio(123);
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
        const entity = new UnidadDeNegocio();
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
