import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFuente, Fuente } from 'app/shared/model/fuente.model';
import { FuenteService } from './fuente.service';

@Component({
  selector: 'jhi-fuente-update',
  templateUrl: './fuente-update.component.html',
})
export class FuenteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required]],
  });

  constructor(protected fuenteService: FuenteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fuente }) => {
      this.updateForm(fuente);
    });
  }

  updateForm(fuente: IFuente): void {
    this.editForm.patchValue({
      id: fuente.id,
      nombre: fuente.nombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fuente = this.createFromForm();
    if (fuente.id !== undefined) {
      this.subscribeToSaveResponse(this.fuenteService.update(fuente));
    } else {
      this.subscribeToSaveResponse(this.fuenteService.create(fuente));
    }
  }

  private createFromForm(): IFuente {
    return {
      ...new Fuente(),
      id: this.editForm.get(['id'])!.value,
      nombre: this.editForm.get(['nombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFuente>>): void {
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
