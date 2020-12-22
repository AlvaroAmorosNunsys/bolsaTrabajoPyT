import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoJornada } from 'app/shared/model/tipo-jornada.model';
import { TipoJornadaService } from './tipo-jornada.service';

@Component({
  templateUrl: './tipo-jornada-delete-dialog.component.html',
})
export class TipoJornadaDeleteDialogComponent {
  tipoJornada?: ITipoJornada;

  constructor(
    protected tipoJornadaService: TipoJornadaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tipoJornadaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tipoJornadaListModification');
      this.activeModal.close();
    });
  }
}
