import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUnidadDeNegocio, UnidadDeNegocio } from 'app/shared/model/unidad-de-negocio.model';
import { UnidadDeNegocioService } from './unidad-de-negocio.service';

@Component({
  selector: 'jhi-unidad-de-negocio-update',
  templateUrl: './unidad-de-negocio-update.component.html',
})
export class UnidadDeNegocioUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required, Validators.maxLength(250)]],
  });

  constructor(
    protected unidadDeNegocioService: UnidadDeNegocioService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ unidadDeNegocio }) => {
      this.updateForm(unidadDeNegocio);
    });
  }

  updateForm(unidadDeNegocio: IUnidadDeNegocio): void {
    this.editForm.patchValue({
      id: unidadDeNegocio.id,
      nombre: unidadDeNegocio.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const unidadDeNegocio = this.createFromForm();
    if (unidadDeNegocio.id !== undefined) {
      this.subscribeToSaveResponse(this.unidadDeNegocioService.update(unidadDeNegocio));
    } else {
      this.subscribeToSaveResponse(this.unidadDeNegocioService.create(unidadDeNegocio));
    }
  }

  private createFromForm(): IUnidadDeNegocio {
    return {
      ...new UnidadDeNegocio(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUnidadDeNegocio>>): void {
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
