import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUnidadDeNegocio } from 'app/shared/model/unidad-de-negocio.model';
import { UnidadDeNegocioService } from './unidad-de-negocio.service';

@Component({
  templateUrl: './unidad-de-negocio-delete-dialog.component.html',
})
export class UnidadDeNegocioDeleteDialogComponent {
  unidadDeNegocio?: IUnidadDeNegocio;

  constructor(
    protected unidadDeNegocioService: UnidadDeNegocioService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.unidadDeNegocioService.delete(id).subscribe(() => {
      this.eventManager.broadcast('unidadDeNegocioListModification');
      this.activeModal.close();
    });
  }
}
