import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IHistorialPosicion, HistorialPosicion } from 'app/shared/model/historial-posicion.model';
import { HistorialPosicionService } from './historial-posicion.service';
import { IEstadoPosicion } from 'app/shared/model/estado-posicion.model';
import { EstadoPosicionService } from 'app/entities/estado-posicion/estado-posicion.service';
import { IPosicion } from 'app/shared/model/posicion.model';
import { PosicionService } from 'app/entities/posicion/posicion.service';

type SelectableEntity = IEstadoPosicion | IPosicion;

@Component({
  selector: 'jhi-historial-posicion-update',
  templateUrl: './historial-posicion-update.component.html',
})
export class HistorialPosicionUpdateComponent implements OnInit {
  isSaving = false;
  estadoposicions: IEstadoPosicion[] = [];
  posicions: IPosicion[] = [];
  fechaCambioDp: any;

  editForm = this.fb.group({
    id: [],
    fechaCambio: [null, [Validators.required]],
    estadoPosicionId: [],
    posicionId: [],
  });

  constructor(
    protected historialPosicionService: HistorialPosicionService,
    protected estadoPosicionService: EstadoPosicionService,
    protected posicionService: PosicionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historialPosicion }) => {
      this.updateForm(historialPosicion);

      this.estadoPosicionService.query().subscribe((res: HttpResponse<IEstadoPosicion[]>) => (this.estadoposicions = res.body || []));

      this.posicionService.query().subscribe((res: HttpResponse<IPosicion[]>) => (this.posicions = res.body || []));
    });
  }

  updateForm(historialPosicion: IHistorialPosicion): void {
    this.editForm.patchValue({
      id: historialPosicion.id,
      fechaCambio: historialPosicion.fechaCambio,
      estadoPosicionId: historialPosicion.estadoPosicionId,
      posicionId: historialPosicion.posicionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const historialPosicion = this.createFromForm();
    if (historialPosicion.id !== undefined) {
      this.subscribeToSaveResponse(this.historialPosicionService.update(historialPosicion));
    } else {
      this.subscribeToSaveResponse(this.historialPosicionService.create(historialPosicion));
    }
  }

  private createFromForm(): IHistorialPosicion {
    return {
      ...new HistorialPosicion(),
      id: this.editForm.get(['id'])!.value,
      fechaCambio: this.editForm.get(['fechaCambio'])!.value,
      estadoPosicionId: this.editForm.get(['estadoPosicionId'])!.value,
      posicionId: this.editForm.get(['posicionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistorialPosicion>>): void {
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
