import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEstadoCandidatura } from 'app/shared/model/estado-candidatura.model';
import { EstadoCandidaturaService } from './estado-candidatura.service';

@Component({
  templateUrl: './estado-candidatura-delete-dialog.component.html',
})
export class EstadoCandidaturaDeleteDialogComponent {
  estadoCandidatura?: IEstadoCandidatura;

  constructor(
    protected estadoCandidaturaService: EstadoCandidaturaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.estadoCandidaturaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('estadoCandidaturaListModification');
      this.activeModal.close();
    });
  }
}
