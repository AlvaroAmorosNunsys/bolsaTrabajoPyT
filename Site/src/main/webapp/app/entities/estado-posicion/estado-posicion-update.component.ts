import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEstadoPosicion, EstadoPosicion } from 'app/shared/model/estado-posicion.model';
import { EstadoPosicionService } from './estado-posicion.service';

@Component({
  selector: 'jhi-estado-posicion-update',
  templateUrl: './estado-posicion-update.component.html',
})
export class EstadoPosicionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required]],
    porDefecto: [null, [Validators.required]],
  });

  constructor(protected estadoPosicionService: EstadoPosicionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadoPosicion }) => {
      this.updateForm(estadoPosicion);
    });
  }

  updateForm(estadoPosicion: IEstadoPosicion): void {
    this.editForm.patchValue({
      id: estadoPosicion.id,
      nombre: estadoPosicion.nombre,
      porDefecto: estadoPosicion.porDefecto,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadoPosicion = this.createFromForm();
    if (estadoPosicion.id !== undefined) {
      this.subscribeToSaveResponse(this.estadoPosicionService.update(estadoPosicion));
    } else {
      this.subscribeToSaveResponse(this.estadoPosicionService.create(estadoPosicion));
    }
  }

  private createFromForm(): IEstadoPosicion {
    return {
      ...new EstadoPosicion(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      porDefecto: this.editForm.get(['porDefecto'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadoPosicion>>): void {
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
