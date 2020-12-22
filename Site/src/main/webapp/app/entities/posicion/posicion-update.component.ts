import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPosicion, Posicion } from 'app/shared/model/posicion.model';
import { PosicionService } from './posicion.service';
import { IEstadoPosicion } from 'app/shared/model/estado-posicion.model';
import { EstadoPosicionService } from 'app/entities/estado-posicion/estado-posicion.service';
import { ITipoJornada } from 'app/shared/model/tipo-jornada.model';
import { TipoJornadaService } from 'app/entities/tipo-jornada/tipo-jornada.service';
import { IUnidadDeNegocio } from 'app/shared/model/unidad-de-negocio.model';
import { UnidadDeNegocioService } from 'app/entities/unidad-de-negocio/unidad-de-negocio.service';
import { AccountService } from 'app/core/auth/account.service';
import { UserService } from 'app/core/user/user.service';
import { UsuarioService } from '../usuario/usuario.service';
import { IUsuario } from 'app/shared/model/usuario.model';

type SelectableEntity = IEstadoPosicion | ITipoJornada | IUnidadDeNegocio;

@Component({
  selector: 'jhi-posicion-update',
  templateUrl: './posicion-update.component.html',
})
export class PosicionUpdateComponent implements OnInit {
  isSaving = false;
  estadoposicions: IEstadoPosicion[] = [];
  tipojornadas: ITipoJornada[] = [];
  unidaddenegocios: IUnidadDeNegocio[] = [];
  fechaAltaDp: any;
  fechaNecesidadDp: any;

  editForm = this.fb.group({
    id: [],
    titulo: [null, [Validators.required, Validators.maxLength(250)]],
    descripcion: [null, [Validators.required, Validators.maxLength(5000)]],
    numeroPuestos: [null, [Validators.required, Validators.min(1)]],
    salarioMinimo: [],
    salarioMaximo: [],
    fechaAlta: [],
    fechaNecesidad: [null, [Validators.required]],
    estadoPosicionId: [null, [Validators.required]],
    tipoJornadaId: [null, [Validators.required]],
    unidadDeNegocioId: [null, [Validators.required]],
  });

  constructor(
    protected posicionService: PosicionService,
    protected estadoPosicionService: EstadoPosicionService,
    protected tipoJornadaService: TipoJornadaService,
    protected unidadDeNegocioService: UnidadDeNegocioService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    protected accountService: AccountService,
    protected usuarioService: UsuarioService,
    protected userService: UserService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ posicion }) => {
      this.updateForm(posicion);

      this.estadoPosicionService.query().subscribe((res: HttpResponse<IEstadoPosicion[]>) => (this.estadoposicions = res.body || []));

      this.tipoJornadaService.query().subscribe((res: HttpResponse<ITipoJornada[]>) => (this.tipojornadas = res.body || []));

      this.unidadDeNegocioService.query().subscribe((res: HttpResponse<IUnidadDeNegocio[]>) => (this.unidaddenegocios = res.body || []));
    });

      // Asignar automaticamente la unidad de negocio a la que pertenece un usuario
      this.accountService.identity().subscribe(account => {
        if (account){
          this.userService.find(account.login).subscribe(resUser => {
            if (resUser) {
              this.usuarioService.find(resUser.id).subscribe((usuario: HttpResponse<IUsuario>) => {
                  if (usuario.body?.unidadDeNegocioId) {
                    this.editForm.controls['unidadDeNegocioId'].setValue(usuario.body.unidadDeNegocioId)
                  }
              });
            }
          })
        }
      })
  }

  updateForm(posicion: IPosicion): void {
    this.editForm.patchValue({
      id: posicion.id,
      titulo: posicion.titulo,
      descripcion: posicion.descripcion,
      numeroPuestos: posicion.numeroPuestos,
      salarioMinimo: posicion.salarioMinimo,
      salarioMaximo: posicion.salarioMaximo,
      fechaAlta: posicion.fechaAlta,
      fechaNecesidad: posicion.fechaNecesidad,
      estadoPosicionId: posicion.estadoPosicionId,
      tipoJornadaId: posicion.tipoJornadaId,
      unidadDeNegocioId: posicion.unidadDeNegocioId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const posicion = this.createFromForm();
    if (posicion.id !== undefined) {
      this.subscribeToSaveResponse(this.posicionService.update(posicion));
    } else {
      this.subscribeToSaveResponse(this.posicionService.create(posicion));
    }
  }

  private createFromForm(): IPosicion {
    return {
      ...new Posicion(),
      id: this.editForm.get(['id'])!.value,
      titulo: this.editForm.get(['titulo'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
      numeroPuestos: this.editForm.get(['numeroPuestos'])!.value,
      salarioMinimo: this.editForm.get(['salarioMinimo'])!.value,
      salarioMaximo: this.editForm.get(['salarioMaximo'])!.value,
      fechaAlta: this.editForm.get(['fechaAlta'])!.value,
      fechaNecesidad: this.editForm.get(['fechaNecesidad'])!.value,
      estadoPosicionId: this.editForm.get(['estadoPosicionId'])!.value,
      tipoJornadaId: this.editForm.get(['tipoJornadaId'])!.value,
      unidadDeNegocioId: this.editForm.get(['unidadDeNegocioId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPosicion>>): void {
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