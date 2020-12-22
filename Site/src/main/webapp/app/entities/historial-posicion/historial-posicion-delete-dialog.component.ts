import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistorialPosicion } from 'app/shared/model/historial-posicion.model';
import { HistorialPosicionService } from './historial-posicion.service';

@Component({
  templateUrl: './historial-posicion-delete-dialog.component.html',
})
export class HistorialPosicionDeleteDialogComponent {
  historialPosicion?: IHistorialPosicion;

  constructor(
    protected historialPosicionService: HistorialPosicionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.historialPosicionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('historialPosicionListModification');
      this.activeModal.close();
    });
  }
}
