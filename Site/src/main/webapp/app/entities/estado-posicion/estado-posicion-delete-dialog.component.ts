import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadoPosicion } from 'app/shared/model/estado-posicion.model';
import { EstadoPosicionService } from './estado-posicion.service';

@Component({
  templateUrl: './estado-posicion-delete-dialog.component.html',
})
export class EstadoPosicionDeleteDialogComponent {
  estadoPosicion?: IEstadoPosicion;

  constructor(
    protected estadoPosicionService: EstadoPosicionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadoPosicionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadoPosicionListModification');
      this.activeModal.close();
    });
  }
}
