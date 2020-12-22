import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FrontTestModule } from '../../../test.module';
import { TipoJornadaUpdateComponent } from 'app/entities/tipo-jornada/tipo-jornada-update.component';
import { TipoJornadaService } from 'app/entities/tipo-jornada/tipo-jornada.service';
import { TipoJornada } from 'app/shared/model/tipo-jornada.model';

describe('Component Tests', () => {
  describe('TipoJornada Management Update Component', () => {
    let comp: TipoJornadaUpdateComponent;
    let fixture: ComponentFixture<TipoJornadaUpdateComponent>;
    let service: TipoJornadaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FrontTestModule],
        declarations: [TipoJornadaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TipoJornadaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TipoJornadaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TipoJornadaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TipoJornada(123);
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
        const entity = new TipoJornada();
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
