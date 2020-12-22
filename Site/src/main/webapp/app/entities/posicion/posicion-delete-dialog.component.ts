import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPosicion } from 'app/shared/model/posicion.model';
import { PosicionService } from './posicion.service';

@Component({
  templateUrl: './posicion-delete-dialog.component.html',
})
export class PosicionDeleteDialogComponent {
  posicion?: IPosicion;

  constructor(protected posicionService: PosicionService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.posicionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('posicionListModification');
      this.activeModal.close();
    });
  }
}
