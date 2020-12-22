import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITipoJornada, TipoJornada } from 'app/shared/model/tipo-jornada.model';
import { TipoJornadaService } from './tipo-jornada.service';

@Component({
  selector: 'jhi-tipo-jornada-update',
  templateUrl: './tipo-jornada-update.component.html',
})
export class TipoJornadaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required, Validators.maxLength(100)]],
  });

  constructor(protected tipoJornadaService: TipoJornadaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoJornada }) => {
      this.updateForm(tipoJornada);
    });
  }

  updateForm(tipoJornada: ITipoJornada): void {
    this.editForm.patchValue({
      id: tipoJornada.id,
      nombre: tipoJornada.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tipoJornada = this.createFromForm();
    if (tipoJornada.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoJornadaService.update(tipoJornada));
    } else {
      this.subscribeToSaveResponse(this.tipoJornadaService.create(tipoJornada));
    }
  }

  private createFromForm(): ITipoJornada {
    return {
      ...new TipoJornada(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoJornada>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
