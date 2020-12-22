import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistorialCandidatura } from 'app/shared/model/historial-candidatura.model';
import { HistorialCandidaturaService } from './historial-candidatura.service';

@Component({
  templateUrl: './historial-candidatura-delete-dialog.component.html',
})
export class HistorialCandidaturaDeleteDialogComponent {
  historialCandidatura?: IHistorialCandidatura;

  constructor(
    protected historialCandidaturaService: HistorialCandidaturaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.historialCandidaturaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('historialCandidaturaListModification');
      this.activeModal.close();
    });
  }
}
