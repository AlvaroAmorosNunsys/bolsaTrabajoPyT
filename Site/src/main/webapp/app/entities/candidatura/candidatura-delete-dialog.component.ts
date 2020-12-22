import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICandidatura } from 'app/shared/model/candidatura.model';
import { CandidaturaService } from './candidatura.service';

@Component({
  templateUrl: './candidatura-delete-dialog.component.html',
})
export class CandidaturaDeleteDialogComponent {
  candidatura?: ICandidatura;

  constructor(
    protected candidaturaService: CandidaturaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.candidaturaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('candidaturaListModification');
      this.activeModal.close();
    });
  }
}
