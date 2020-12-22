import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFuente } from 'app/shared/model/fuente.model';
import { FuenteService } from './fuente.service';

@Component({
  templateUrl: './fuente-delete-dialog.component.html',
})
export class FuenteDeleteDialogComponent {
  fuente?: IFuente;

  constructor(protected fuenteService: FuenteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fuenteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fuenteListModification');
      this.activeModal.close();
    });
  }
}
