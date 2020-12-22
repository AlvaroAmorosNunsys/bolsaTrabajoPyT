import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IHistorialCandidatura, HistorialCandidatura } from 'app/shared/model/historial-candidatura.model';
import { HistorialCandidaturaService } from './historial-candidatura.service';
import { IPosicion } from 'app/shared/model/posicion.model';
import { PosicionService } from 'app/entities/posicion/posicion.service';
import { ICandidatura } from 'app/shared/model/candidatura.model';
import { CandidaturaService } from 'app/entities/candidatura/candidatura.service';
import { IEstadoCandidatura } from 'app/shared/model/estado-candidatura.model';
import { EstadoCandidaturaService } from 'app/entities/estado-candidatura/estado-candidatura.service';

type SelectableEntity = IPosicion | ICandidatura | IEstadoCandidatura;

@Component({
  selector: 'jhi-historial-candidatura-update',
  templateUrl: './historial-candidatura-update.component.html',
})
export class HistorialCandidaturaUpdateComponent implements OnInit {
  isSaving = false;
  posicions: IPosicion[] = [];
  candidaturas: ICandidatura[] = [];
  estadocandidaturas: IEstadoCandidatura[] = [];
  fechaCambioDp: any;

  editForm = this.fb.group({
    id: [],
    fechaCambio: [null, [Validators.required]],
    posicionId: [],
    candidaturaId: [],
    estadoCandidaturaId: [],
  });

  constructor(
    protected historialCandidaturaService: HistorialCandidaturaService,
    protected posicionService: PosicionService,
    protected candidaturaService: CandidaturaService,
    protected estadoCandidaturaService: EstadoCandidaturaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historialCandidatura }) => {
      this.updateForm(historialCandidatura);

      this.posicionService.query().subscribe((res: HttpResponse<IPosicion[]>) => (this.posicions = res.body || []));

      this.candidaturaService.query().subscribe((res: HttpResponse<ICandidatura[]>) => (this.candidaturas = res.body || []));

      this.estadoCandidaturaService
        .query()
        .subscribe((res: HttpResponse<IEstadoCandidatura[]>) => (this.estadocandidaturas = res.body || []));
    });
  }

  updateForm(historialCandidatura: IHistorialCandidatura): void {
    this.editForm.patchValue({
      id: historialCandidatura.id,
      fechaCambio: historialCandidatura.fechaCambio,
      posicionId: historialCandidatura.posicionId,
      candidaturaId: historialCandidatura.candidaturaId,
      estadoCandidaturaId: historialCandidatura.estadoCandidaturaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const historialCandidatura = this.createFromForm();
    if (historialCandidatura.id !== undefined) {
      this.subscribeToSaveResponse(this.historialCandidaturaService.update(historialCandidatura));
    } else {
      this.subscribeToSaveResponse(this.historialCandidaturaService.create(historialCandidatura));
    }
  }

  private createFromForm(): IHistorialCandidatura {
    return {
      ...new HistorialCandidatura(),
      id: this.editForm.get(['id'])!.value,
      fechaCambio: this.editForm.get(['fechaCambio'])!.value,
      posicionId: this.editForm.get(['posicionId'])!.value,
      candidaturaId: this.editForm.get(['candidaturaId'])!.value,
      estadoCandidaturaId: this.editForm.get(['estadoCandidaturaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistorialCandidatura>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
