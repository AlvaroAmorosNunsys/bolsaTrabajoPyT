import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEstadoCandidatura, EstadoCandidatura } from 'app/shared/model/estado-candidatura.model';
import { EstadoCandidaturaService } from './estado-candidatura.service';

@Component({
  selector: 'jhi-estado-candidatura-update',
  templateUrl: './estado-candidatura-update.component.html',
})
export class EstadoCandidaturaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required]],
    porDefecto: [null, [Validators.required]],
  });

  constructor(
    protected estadoCandidaturaService: EstadoCandidaturaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estadoCandidatura }) => {
      this.updateForm(estadoCandidatura);
    });
  }

  updateForm(estadoCandidatura: IEstadoCandidatura): void {
    this.editForm.patchValue({
      id: estadoCandidatura.id,
      nombre: estadoCandidatura.nombre,
      porDefecto: estadoCandidatura.porDefecto,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estadoCandidatura = this.createFromForm();
    if (estadoCandidatura.id !== undefined) {
      this.subscribeToSaveResponse(this.estadoCandidaturaService.update(estadoCandidatura));
    } else {
      this.subscribeToSaveResponse(this.estadoCandidaturaService.create(estadoCandidatura));
    }
  }

  private createFromForm(): IEstadoCandidatura {
    return {
      ...new EstadoCandidatura(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
      porDefecto: this.editForm.get(['porDefecto'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstadoCandidatura>>): void {
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
