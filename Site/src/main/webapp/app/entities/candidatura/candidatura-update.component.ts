import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICandidatura, Candidatura } from 'app/shared/model/candidatura.model';
import { CandidaturaService } from './candidatura.service';
import { IEstadoCandidatura } from 'app/shared/model/estado-candidatura.model';
import { EstadoCandidaturaService } from 'app/entities/estado-candidatura/estado-candidatura.service';
import { IFuente } from 'app/shared/model/fuente.model';
import { FuenteService } from 'app/entities/fuente/fuente.service';
import { IPosicion } from 'app/shared/model/posicion.model';
import { PosicionService } from 'app/entities/posicion/posicion.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

type SelectableEntity = IEstadoCandidatura | IFuente | IPosicion | IPersona;

@Component({
  selector: 'jhi-candidatura-update',
  templateUrl: './candidatura-update.component.html',
})
export class CandidaturaUpdateComponent implements OnInit {
  isSaving = false;
  estadocandidaturas: IEstadoCandidatura[] = [];
  fuentes: IFuente[] = [];
  posicions: IPosicion[] = [];
  personas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    estadoCandidaturaId: [],
    fuenteId: [],
    posicionId: [],
    personaId: [],
  });

  constructor(
    protected candidaturaService: CandidaturaService,
    protected estadoCandidaturaService: EstadoCandidaturaService,
    protected fuenteService: FuenteService,
    protected posicionService: PosicionService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ candidatura }) => {
      this.updateForm(candidatura);

      this.estadoCandidaturaService
        .query()
        .subscribe((res: HttpResponse<IEstadoCandidatura[]>) => (this.estadocandidaturas = res.body || []));

      this.fuenteService.query().subscribe((res: HttpResponse<IFuente[]>) => (this.fuentes = res.body || []));

      this.posicionService.query().subscribe((res: HttpResponse<IPosicion[]>) => (this.posicions = res.body || []));

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));
    });
  }

  updateForm(candidatura: ICandidatura): void {
    this.editForm.patchValue({
      id: candidatura.id,
      estadoCandidaturaId: candidatura.estadoCandidaturaId,
      fuenteId: candidatura.fuenteId,
      posicionId: candidatura.posicionId,
      personaId: candidatura.personaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const candidatura = this.createFromForm();
    if (candidatura.id !== undefined) {
      this.subscribeToSaveResponse(this.candidaturaService.update(candidatura));
    } else {
      this.subscribeToSaveResponse(this.candidaturaService.create(candidatura));
    }
  }

  private createFromForm(): ICandidatura {
    return {
      ...new Candidatura(),
      id: this.editForm.get(['id'])!.value,
      estadoCandidaturaId: this.editForm.get(['estadoCandidaturaId'])!.value,
      fuenteId: this.editForm.get(['fuenteId'])!.value,
      posicionId: this.editForm.get(['posicionId'])!.value,
      personaId: this.editForm.get(['personaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICandidatura>>): void {
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
